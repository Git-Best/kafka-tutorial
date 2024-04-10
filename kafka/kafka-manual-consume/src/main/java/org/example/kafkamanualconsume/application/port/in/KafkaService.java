package org.example.kafkamanualconsume.application.port.in;


import org.example.kafkamanualconsume.adaptor.in.web.dto.MessageData;

public interface KafkaService {
    void sendMessage(MessageData message);
}
