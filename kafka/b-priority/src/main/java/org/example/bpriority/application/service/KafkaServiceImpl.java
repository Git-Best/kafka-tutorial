package org.example.bpriority.application.service;

import lombok.RequiredArgsConstructor;
import org.example.bpriority.adaptor.in.web.dto.MessageData;
import org.example.bpriority.application.port.in.KafkaService;
import org.example.bpriority.application.port.out.KafkaPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaServiceImpl implements KafkaService {

    private final KafkaPort kafkaPort;

    @Value("${spring.kafka.topic-with-key}")
    private String topicWithKey;

    @Override
    public void sendMessage(String key, MessageData message) {
        kafkaPort.sendMessage(topicWithKey, key, message);
    }
}
