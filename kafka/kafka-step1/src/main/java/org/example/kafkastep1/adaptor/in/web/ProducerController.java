package org.example.kafkastep1.adaptor.in.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.kafkastep1.adaptor.in.web.dto.MessageData;
import org.example.kafkastep1.application.port.in.KafkaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class ProducerController {

    private final KafkaService kafkaService;

    @PostMapping("/produce")
    public ResponseEntity<MessageData> produceMessage(@RequestBody MessageData message) {
        message.setTime(LocalDateTime.now());

        kafkaService.sendMessage(message);

        return ResponseEntity.ok(message);
    }
}
