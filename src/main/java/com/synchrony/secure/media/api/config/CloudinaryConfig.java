package com.synchrony.secure.media.api.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary getCloudinary(){
        Map<String,String> config = new HashMap<>();
        config.put("cloud_name","dbsuf7wds");
        config.put("api_key","927367418956697");
        config.put("api_secret","E7b8fTwhap0GYHHgCCsX8TKjJNA");
        return new Cloudinary(config);
    }
}
