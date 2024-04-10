package org.example.apartionkey.adaptor.in.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apartionkey.adaptor.in.web.dto.MessageData;
import org.example.apartionkey.application.port.in.KafkaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/producer")
@RequiredArgsConstructor
@Slf4j
public class Controller {

    private final KafkaService kafkaService;

    @PostMapping("produce-with-key/{key}")
    public ResponseEntity<?> produceMessageWithKey(@PathVariable("key") String key, @RequestBody MessageData messageData) {
        messageData.setTime(LocalDateTime.now());

        kafkaService.sendMessage(key, messageData);

        return ResponseEntity.ok(messageData);
    }
}
