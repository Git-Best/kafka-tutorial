package org.example.bpriority.adaptor.in.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Consumer {

    @KafkaListener(topics = "${spring.kafka.topic-with-priority}", containerFactory = "highPriorityKafkaListenerContainerFactory")
    public void listenPriorityTopic(Object record) {
        log.info("Received high priority message: {}", record);
    }

    @KafkaListener(topics = "${spring.kafka.topic-with-priority}", containerFactory = "normalPriorityKafkaListenerContainerFactory")
    public void listenNonPriorityTopic(Object record) {
        log.info("Received normal priority message: {}", record);
    }
}
