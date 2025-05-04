package com.synchrony.secure.media.api.controller;

import com.synchrony.secure.media.api.dto.RegisterRequest;
import com.synchrony.secure.media.api.kafka.ImageEventPublisher;
import com.synchrony.secure.media.api.model.Users;
import com.synchrony.secure.media.api.service.CloudinaryImageService;
import com.synchrony.secure.media.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private ImageEventPublisher eventPublisher;
    @Autowired
    private UserService userService;
    @Autowired
    private CloudinaryImageService imageService;

    @PostMapping("/register")
    public ResponseEntity<Users> register(@RequestBody RegisterRequest request) {
        logger.info("Registering user: {}", request.username());
        return ResponseEntity.ok(userService.register(request));

    }

    @PostMapping("/{username}/upload")
    public ResponseEntity<?> upload(@PathVariable String username,
                                    @RequestParam String password,
                                    @RequestParam MultipartFile image) throws IOException {
        logger.info("Uploading image for user: {}", username);
        var userOpt = userService.authenticate(username, password);
        if (userOpt.isEmpty()) {
            logger.warn("Unauthorized upload attempt for user: {}", username);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        // Upload to Cloudinary
        Map<String, String> result = imageService.upload(image);
        String url = result.get("secure_url");
        String imageName = result.get("public_id"); // This is the unique image ID from Cloudinary


//        String url = imageService.upload(image).toString();
        Users user = userOpt.get();
        user.getImageUrls().add(url);
        userService.save(user);
        logger.info("Image uploaded and associated with user: {}", username);
        // ✅ Publish Kafka event (username and image name)
        eventPublisher.publishImageUpload(username, imageName);
        return ResponseEntity.ok(url);
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUserInfo(@PathVariable String username,
                                         @RequestParam String password) {
        var userOpt = userService.authenticate(username, password);
        if (userOpt.isEmpty()) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        return ResponseEntity.ok(userOpt.get());
    }

    @DeleteMapping("/{username}/images/delete")
    public ResponseEntity<?> deleteImage(@PathVariable String username,
                                         @RequestParam String password,
                                         @RequestParam String publicId) throws IOException {
        logger.info("Attempting image delete for user: {}, publicId: {}", username, publicId);
        var userOpt = userService.authenticate(username, password);
        if (userOpt.isEmpty()) {
            logger.warn("Unauthorized image delete attempt for user: {}", username);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        // Delete from Cloudinary
        String result = imageService.delete(publicId);
        if (!"ok".equals(result)) {
            logger.error("Cloudinary deletion failed for publicId: {}", publicId);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete image from Cloudinary");
        }

        // Remove image URL from user's list
        Users user = userOpt.get();
        user.getImageUrls().removeIf(url -> url.contains(publicId));
        userService.save(user);
        logger.info("Image deleted successfully for user: {}", username);
        return ResponseEntity.ok("Image deleted successfully");
    }
}
