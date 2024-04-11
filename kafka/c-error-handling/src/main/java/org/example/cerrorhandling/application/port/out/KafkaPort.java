package org.example.cerrorhandling.application.port.out;

import org.example.cerrorhandling.adaptor.in.web.dto.MessageData;

public interface KafkaPort {
    void sendMessage(String topic, MessageData message);
}
