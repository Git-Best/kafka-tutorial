package org.example.kafkastep1.application.port.in;

import org.example.kafkastep1.adaptor.in.web.dto.MessageData;

public interface KafkaService {
    void sendMessage(MessageData message);
}
