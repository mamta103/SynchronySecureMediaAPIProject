package com.synchrony.secure.media.api.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * Service implementation for managing image uploads and deletions using Cloudinary.
 * This class provides methods to upload images to Cloudinary and delete images
 * from Cloudinary by their public ID.
 */
@Service
public class CloudinaryImageServiceImpl implements CloudinaryImageService {

    /**
     * Cloudinary instance used for interacting with the Cloudinary API.
     * This instance is automatically injected by Spring.
     */
    @Autowired
    private Cloudinary cloudinary;

    /**
     * Uploads an image file to Cloudinary.
     *
     * @param file The image file to be uploaded, represented as a MultipartFile.
     * @return A map containing the upload result, such as the URL and public ID of the uploaded image.
     * @throws IOException If an I/O error occurs during the upload process.
     */
    @Override
    public Map<String, String> upload(MultipartFile file) throws IOException {
        return cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
    }

    /**
     * Deletes an image from Cloudinary by its public ID.
     *
     * @param publicId The public ID of the image to be deleted.
     * @return A string indicating the result of the deletion operation.
     * @throws IOException If an I/O error occurs during the deletion process.
     */
    @Override
    public String delete(String publicId) throws IOException {
        Map<String, String> result = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        return result.get("result");
    }
}