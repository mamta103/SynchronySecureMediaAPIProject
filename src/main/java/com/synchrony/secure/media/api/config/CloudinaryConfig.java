package com.synchrony.secure.media.api.config;

        import com.cloudinary.Cloudinary;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;

        import java.util.HashMap;
        import java.util.Map;

        /**
         * Configuration class for setting up Cloudinary integration.
         * This class provides a Cloudinary bean that can be used throughout the application
         * to interact with the Cloudinary API for media management.
         */
        @Configuration
        public class CloudinaryConfig {

            /**
             * Creates and configures a Cloudinary instance.
             *
             * @return A configured Cloudinary instance.
             */
            @Bean
            public Cloudinary getCloudinary() {
                // Create a map to hold the Cloudinary configuration parameters
                Map<String, String> config = new HashMap<>();

                // Set the Cloudinary cloud name
                config.put("cloud_name", "dbsuf7wds");

                // Set the Cloudinary API key
                config.put("api_key", "927367418956697");

                // Set the Cloudinary API secret
                config.put("api_secret", "E7b8fTwhap0GYHHgCCsX8TKjJNA");

                // Return a new Cloudinary instance with the provided configuration
                return new Cloudinary(config);
            }
        }