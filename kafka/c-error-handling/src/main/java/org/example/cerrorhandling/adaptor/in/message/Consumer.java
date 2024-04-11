package org.example.cerrorhandling.adaptor.in.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Consumer {

    @KafkaListener(topics = "${spring.kafka.topic-default-error}", containerFactory = "errorCommonHandlingKafkaListenerContainerFactory")
    public void listenForDefaultErrorHandler(Object object) {
        log.info("Received message from default error handler topic: {}", object);
        throw new RuntimeException("Error occurred in default error handler topic");
    }

    @KafkaListener(topics = "${kafka.topic-error-handle}", containerFactory = "errorHandlingKafkaListenerContainerFactory")
    public void listenForErrorHandle(Object record) {
        log.info("Receive Message for Error Handler, It will occur error: {}", record);
        throw new RuntimeException("Consumer Error and Exception Occurs.");
    }
}
