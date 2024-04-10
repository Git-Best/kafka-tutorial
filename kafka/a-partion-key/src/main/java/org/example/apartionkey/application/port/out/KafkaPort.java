package org.example.apartionkey.application.port.out;

import org.example.apartionkey.adaptor.in.web.dto.MessageData;

public interface KafkaPort {
    void sendMessage(String topic, String key, MessageData message);
}
