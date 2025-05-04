package com.synchrony.secure.media.api.kafka;

    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.kafka.core.KafkaTemplate;
    import org.springframework.stereotype.Service;

    /**
     * Service responsible for publishing image upload events to a Kafka topic.
     * This class is used to send messages to the "image-uploads" Kafka topic
     * whenever an image is uploaded by a user.
     */
    @Service
    public class ImageEventPublisher {

        private static final Logger logger = LoggerFactory.getLogger(ImageEventPublisher.class);

        /**
         * The name of the Kafka topic to which image upload events are published.
         */
        private static final String TOPIC = "image-uploads";

        /**
         * KafkaTemplate for sending messages to the Kafka topic.
         * This template is configured to send String key-value pairs.
         */
        @Autowired
        private KafkaTemplate<String, String> kafkaTemplate;

        /**
         * Publishes an image upload event to the Kafka topic.
         *
         * @param username  The username of the user who uploaded the image.
         * @param imageName The name of the image that was uploaded.
         */
        public void publishImageUpload(String username, String imageName) {
            // Create a JSON-formatted message containing the username and image name
            String message = String.format("{\"username\":\"%s\",\"imageName\":\"%s\"}", username, imageName);

            // Send the message to the Kafka topic
            kafkaTemplate.send(TOPIC, message);

            // Log the successful publishing of the message
            logger.info("✅ Published Kafka message to topic '{}': {}", TOPIC, message);
        }
    }