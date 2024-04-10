package org.example.apartionkey.infrastructure.message.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
@RequiredArgsConstructor
public class TopicConfig {

    public final static String DEFAULT_TOPIC = "default-topic";

    @Value("${spring.kafka.topic-with-key}")
    public String TOPIC_WITH_KEY;

    private final KafkaAdmin kafkaAdmin;

    private NewTopic defaultTopic() {
        return TopicBuilder.name(DEFAULT_TOPIC)
                .partitions(1)
                .replicas(1)
                .build();
    }

    private NewTopic topicWithKey() {
        return TopicBuilder.name(TOPIC_WITH_KEY)
                .partitions(3)
                .replicas(3)
                .build();
    }

    @PostConstruct
    public void init() {
        kafkaAdmin.createOrModifyTopics(defaultTopic());
        kafkaAdmin.createOrModifyTopics(topicWithKey());
    }
}
