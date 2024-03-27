package org.example.kafkamanualconsume.application.port.out;


import org.example.kafkamanualconsume.adaptor.in.web.dto.MessageData;

public interface KafkaPort {
    void sendMessage(String topic, MessageData message);
}
