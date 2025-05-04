package com.synchrony.secure.media.api.service;

    import org.springframework.web.multipart.MultipartFile;

    import java.io.IOException;
    import java.util.Map;

    /**
     * Service interface for managing image uploads and deletions using Cloudinary.
     * This interface defines methods for uploading images to Cloudinary and
     * deleting images from Cloudinary by their public ID.
     */
    public interface CloudinaryImageService {

        /**
         * Uploads an image file to Cloudinary.
         *
         * @param file The image file to be uploaded, represented as a `MultipartFile`.
         * @return A map containing the upload result, such as the URL and public ID of the uploaded image.
         * @throws IOException If an I/O error occurs during the upload process.
         */
        Map<String, String> upload(MultipartFile file) throws IOException;

        /**
         * Deletes an image from Cloudinary by its public ID.
         *
         * @param publicId The public ID of the image to be deleted.
         * @return A string indicating the result of the deletion operation.
         * @throws IOException If an I/O error occurs during the deletion process.
         */
        String delete(String publicId) throws IOException;
    }