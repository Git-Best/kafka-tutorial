package org.example.apartionkey.adaptor.in.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Consumer {

    @KafkaListener(topics = "${spring.kafka.topic-with-key}", containerFactory = "defaultKafkaListenerContainerFactory")
    public void listenTopicWithKey(Object record) {
        log.info("Receive Message from {}, values {} with key", record);
    }
}
