package com.synchrony.secure.media.api.service;

    import org.springframework.web.multipart.MultipartFile;

    import java.io.IOException;
    import java.util.Map;

    public interface CloudinaryImageService {
        Map<String, String> upload(MultipartFile file) throws IOException;
        String delete(String publicId) throws IOException;
    }