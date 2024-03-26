package org.example.kafkastep1.application.port.out;

import org.example.kafkastep1.adaptor.in.web.dto.MessageData;

public interface KafkaPort {
    void sendMessage(String topic, MessageData message);
}
