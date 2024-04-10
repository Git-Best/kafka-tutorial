package org.example.apartionkey.adaptor.out.message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apartionkey.adaptor.in.web.dto.MessageData;
import org.example.apartionkey.application.port.out.KafkaPort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Slf4j
@Component
public class Producer implements KafkaPort {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void sendMessage(String topic, String key, MessageData message) {
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, key, message);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Message sent successfully: " + result);
            } else {
                log.error("Failed to send message: " + ex.getMessage());
            }
        });
    }
}
