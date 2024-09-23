package com.lilka.consumer.consumer;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaListenerConsumer {
    @KafkaListener(
            // Определяет группу консюмера
            id = "${kafka.id}",
            // Определяет топик откуда читаем
            topics = "${kafka.topics.test-topic}",
            // ВАЖНО: определяет фабрику, которую мы используем. Иначе используется фабрика по умолчанию и многопоточность не работает
            containerFactory = "kafkaListenerContainerFactory")
    public void handle(@Payload String message) {
        readMessage(message);
    }

    public void readMessage(String message) {
        log.info("message {} read", message);
    }
}
