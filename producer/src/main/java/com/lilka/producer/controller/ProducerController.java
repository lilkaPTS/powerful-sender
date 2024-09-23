package com.lilka.producer.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProducerController {

    @Value("${kafka.topics.test-topic}")
    private String topic;

    private final KafkaTemplate<Object, Object> kafkaTemplate;

    @PostMapping ("/sendMessage")
    public ResponseEntity<String> sendMessages(String message) {
        kafkaTemplate.send(topic, message);
        log.info("Message {} sent", message);
        return ResponseEntity.ok("ok");
    }
}
