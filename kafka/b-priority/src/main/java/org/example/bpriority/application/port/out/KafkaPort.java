package org.example.bpriority.application.port.out;

import org.example.bpriority.adaptor.in.web.dto.MessageData;

public interface KafkaPort {
    void sendMessage(String topic, String key, MessageData message);
}
