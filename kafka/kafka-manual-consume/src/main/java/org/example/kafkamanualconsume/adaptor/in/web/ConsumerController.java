package org.example.kafkamanualconsume.adaptor.in.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.kafkamanualconsume.adaptor.in.message.ManualConsumerService;
import org.example.kafkamanualconsume.infrastructure.message.KafkaTopicConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/consumer")
@Slf4j
@RequiredArgsConstructor
public class ConsumerController {

    private final ManualConsumerService manualConsumerService;

    @GetMapping("/consume")
    public ResponseEntity<?> getMessage(@RequestParam(value = "partition", required = false, defaultValue = "0") Integer partition,
                                       @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset) {
        return ResponseEntity.ok(manualConsumerService.receiveMessage(KafkaTopicConfig.DEFAULT_TOPIC, partition, offset));
    }
}
