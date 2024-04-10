package org.example.kafkamanualconsume.adaptor.in.message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.TopicPartition;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.stream.StreamSupport;

@Slf4j
@Component
@RequiredArgsConstructor
public class ManualConsumerService {

    private final Consumer<String, Object> manualConsumer;

    public List<Object> receiveMessage(String topicName, int partition, int offset) {
        TopicPartition topicPartition = new TopicPartition(topicName, partition);
        manualConsumer.assign(List.of(topicPartition));
        // seek 메소드의 경우 특정 파티션의 특정 오프셋에 해당하는 값을 획득할 수 있다
        manualConsumer.seek(topicPartition, offset);

        // poll 메소드는 실제 파티션에서 레코드를 획득한다.
        ConsumerRecords<String, Object> records = manualConsumer.poll(Duration.ofMillis(1000));

        for(var record : records) {
            log.info("Received message: " + record.value());
        }

        manualConsumer.unsubscribe();
        return StreamSupport.stream(records.spliterator(), false).map(ConsumerRecord::value).toList();
    }
}
