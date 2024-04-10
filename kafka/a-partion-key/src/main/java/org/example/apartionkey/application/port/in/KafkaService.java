package org.example.apartionkey.application.port.in;

import org.example.apartionkey.adaptor.in.web.dto.MessageData;

public interface KafkaService {
    void sendMessage(String key, MessageData message);
}
