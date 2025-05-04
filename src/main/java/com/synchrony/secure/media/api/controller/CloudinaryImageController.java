package com.synchrony.secure.media.api.controller;

import com.synchrony.secure.media.api.service.CloudinaryImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * REST controller for handling image uploads to Cloudinary.
 * This controller provides an endpoint to upload images and receive
 * the response data from Cloudinary.
 */
@RestController
@RequestMapping("/cloudinary/upload")
public class CloudinaryImageController {

    /**
     * Service for handling Cloudinary image operations.
     */
    @Autowired
    private CloudinaryImageService service;

    /**
     * Endpoint for uploading an image to Cloudinary.
     *
     * @param file The image file to be uploaded, provided as a {@link MultipartFile}.
     * @return A {@link ResponseEntity} containing a map of response data from Cloudinary
     *         and an HTTP status of OK.
     * @throws IOException If an error occurs during file upload.
     */
    @PostMapping
    public ResponseEntity<Map<String, String>> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        // Call the service to upload the image and retrieve the response data
        Map<String, String> data = this.service.upload(file);
        // Return the response data with an HTTP status of OK
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}