package com.synchrony.secure.media.api.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ImageEventConsumer {
    @KafkaListener(topics = "image-uploads", groupId = "media-consumer-group")
    public void consume(String message) {
        System.out.println("Received Kafka message: " + message);
    }

}
