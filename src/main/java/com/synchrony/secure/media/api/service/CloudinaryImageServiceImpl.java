package com.synchrony.secure.media.api.service;

        import com.cloudinary.Cloudinary;
        import com.cloudinary.utils.ObjectUtils;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;
        import org.springframework.web.multipart.MultipartFile;

        import java.io.IOException;
        import java.util.Map;

        @Service
        public class CloudinaryImageServiceImpl implements CloudinaryImageService {
            @Autowired
            private Cloudinary cloudinary;

            @Override
            public Map<String, String> upload(MultipartFile file) throws IOException {
                return cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            }

            @Override
            public String delete(String publicId) throws IOException {
                Map<String, String> result = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
                return result.get("result");
            }
        }