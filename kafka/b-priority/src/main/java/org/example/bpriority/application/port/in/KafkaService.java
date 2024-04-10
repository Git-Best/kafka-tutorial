package org.example.bpriority.application.port.in;

import org.example.bpriority.adaptor.in.web.dto.MessageData;

public interface KafkaService {
    void sendMessage(String key, MessageData message);
}
