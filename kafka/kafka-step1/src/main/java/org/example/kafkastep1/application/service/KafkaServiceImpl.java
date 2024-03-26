package org.example.kafkastep1.application.service;

import lombok.RequiredArgsConstructor;
import org.example.kafkastep1.adaptor.in.web.dto.MessageData;
import org.example.kafkastep1.application.port.in.KafkaService;
import org.example.kafkastep1.application.port.out.KafkaPort;
import org.example.kafkastep1.infrastructure.message.KafkaTopicConfig;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaServiceImpl implements KafkaService {

    private final KafkaPort kafkaPort;

    @Override
    public void sendMessage(MessageData message) {
        kafkaPort.sendMessage(KafkaTopicConfig.DEFAULT_TOPIC, message);
    }
}
