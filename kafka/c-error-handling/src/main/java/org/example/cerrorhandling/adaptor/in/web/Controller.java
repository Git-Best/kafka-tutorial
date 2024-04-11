package org.example.cerrorhandling.adaptor.in.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.cerrorhandling.adaptor.in.web.dto.MessageData;
import org.example.cerrorhandling.application.port.in.KafkaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/producer")
@RequiredArgsConstructor
@Slf4j
public class Controller {

    private final KafkaService kafkaService;

    @PostMapping("produce_error/{category}")
    public ResponseEntity<?> produceMessage(@PathVariable("category") String category, @RequestBody MessageData messageData) {
        messageData.setTime(LocalDateTime.now());

        kafkaService.sendMessage(category, messageData);

        return ResponseEntity.ok(messageData);
    }
}
