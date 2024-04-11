package org.example.cerrorhandling.config;

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

    @Value("${spring.kafka.topic-default-priority}")
    public String TOPIC_COMMON_ERROR;

    @Value("${spring.kafka.topic-error-handle}")
    public String TOPIC_ERROR_HANDLER;

    @Value("${spring.kafka.topic-retry-handle}")
    public String TOPIC_RETRY_HANDLER;

    private final KafkaAdmin kafkaAdmin;

    private NewTopic defaultTopic() {
        return TopicBuilder.name(DEFAULT_TOPIC)
                .partitions(1)
                .replicas(1)
                .build();
    }

    private NewTopic topicCommonError() {
        return TopicBuilder.name(TOPIC_COMMON_ERROR)
                .partitions(3)
                .replicas(2)
                .build();
    }

    private NewTopic topicErrorHandle() {
        return TopicBuilder.name(TOPIC_ERROR_HANDLER)
                .partitions(3)
                .replicas(2)
                .build();
    }

    private NewTopic topicRetryHandle() {
        return TopicBuilder.name(TOPIC_RETRY_HANDLER)
                .partitions(3)
                .replicas(2)
                .build();
    }

    @PostConstruct
    public void init() {
        kafkaAdmin.createOrModifyTopics(defaultTopic());
        kafkaAdmin.createOrModifyTopics(topicCommonError());
        kafkaAdmin.createOrModifyTopics(topicErrorHandle());
        kafkaAdmin.createOrModifyTopics(topicRetryHandle());
    }
}
