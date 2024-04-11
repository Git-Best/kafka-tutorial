package org.example.cerrorhandling.application.service;

import lombok.RequiredArgsConstructor;
import org.example.cerrorhandling.adaptor.in.web.dto.MessageData;
import org.example.cerrorhandling.application.port.in.KafkaService;
import org.example.cerrorhandling.application.port.out.KafkaPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static org.example.cerrorhandling.config.TopicConfig.DEFAULT_TOPIC;

@Service
@RequiredArgsConstructor
public class KafkaServiceImpl implements KafkaService {

    private final KafkaPort kafkaPort;

    @Value("${spring.kafka.topic-default-priority}")
    public String TOPIC_COMMON_ERROR;

    @Value("${spring.kafka.topic-error-handle}")
    public String TOPIC_ERROR_HANDLER;

    @Value("${spring.kafka.topic-retry-handle}")
    public String TOPIC_RETRY_HANDLER;

    @Override
    public void sendMessage(String category, MessageData message) {
        String topic = "";
        if ("default-error".equals(category)) {
            topic = TOPIC_COMMON_ERROR;
        } else if ("error-handler".equals(category)) {
            topic = TOPIC_ERROR_HANDLER;
        } else if ("retry-handler".equals(category)) {
            topic = TOPIC_RETRY_HANDLER;
        } else {
            topic = DEFAULT_TOPIC;
        }
        kafkaPort.sendMessage(topic, message);
    }
}
