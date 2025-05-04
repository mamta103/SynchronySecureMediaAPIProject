package com.synchrony.secure.media.api.kafka;

    import org.springframework.kafka.annotation.KafkaListener;
    import org.springframework.stereotype.Component;

    /**
     * Component responsible for consuming messages from a Kafka topic.
     * This class listens to the specified Kafka topic and processes incoming messages.
     */
    @Component
    public class ImageEventConsumer {

        /**
         * Consumes messages from the "image-uploads" Kafka topic.
         *
         * - Listens to the "image-uploads" topic.
         * - Belongs to the consumer group "media-consumer-group".
         * - Processes the received message by printing it to the console.
         *
         * @param message The message received from the Kafka topic.
         */
        @KafkaListener(topics = "image-uploads", groupId = "media-consumer-group")
        public void consume(String message) {
            System.out.println("Received Kafka message: " + message);
        }
    }