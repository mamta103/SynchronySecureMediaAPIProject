package com.synchrony.secure.media.api.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ImageEventPublisher {
    private static final Logger logger = LoggerFactory.getLogger(ImageEventPublisher.class);
    private static final String TOPIC = "image-uploads";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void publishImageUpload(String username, String imageName) {
        String message = String.format("{\"username\":\"%s\",\"imageName\":\"%s\"}", username, imageName);
        kafkaTemplate.send(TOPIC, message);
        logger.info("✅ Published Kafka message to topic '{}': {}", TOPIC, message);
    }
}