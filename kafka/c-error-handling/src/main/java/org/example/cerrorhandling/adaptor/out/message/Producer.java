package org.example.cerrorhandling.adaptor.out.message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.cerrorhandling.adaptor.in.web.dto.MessageData;
import org.example.cerrorhandling.application.port.out.KafkaPort;
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
    public void sendMessage(String topic, MessageData message) {
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, message);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Send message with offset: {}, partition: {}", result.getRecordMetadata().offset(), result.getRecordMetadata().partition());
            } else {
                log.error("Fail to send message to broker: {}", ex.getMessage());
            }
        });
    }
}
