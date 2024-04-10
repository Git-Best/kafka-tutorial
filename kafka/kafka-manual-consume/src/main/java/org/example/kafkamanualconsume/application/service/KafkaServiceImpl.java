package org.example.kafkamanualconsume.application.service;

import lombok.RequiredArgsConstructor;
import org.example.kafkamanualconsume.adaptor.in.web.dto.MessageData;
import org.example.kafkamanualconsume.application.port.in.KafkaService;
import org.example.kafkamanualconsume.application.port.out.KafkaPort;
import org.example.kafkamanualconsume.infrastructure.message.KafkaTopicConfig;
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
