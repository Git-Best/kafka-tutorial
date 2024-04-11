package org.example.cerrorhandling.application.port.in;

import org.example.cerrorhandling.adaptor.in.web.dto.MessageData;

public interface KafkaService {
    void sendMessage(String category, MessageData message);
}
