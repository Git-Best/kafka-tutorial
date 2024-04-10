package org.example.kafkamanualconsume.adaptor.in.message;

import lombok.extern.slf4j.Slf4j;
import org.example.kafkamanualconsume.infrastructure.message.KafkaTopicConfig;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageListener {

    @KafkaListener(topics = KafkaTopicConfig.DEFAULT_TOPIC, containerFactory = "defaultKafkaListenerContainerFactory")
    public void listenDefaultTopic(Object record) {
        log.info("Received message: " + record);
    }
}
