package org.example.cerrorhandling.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.util.backoff.FixedBackOff;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Slf4j
@Configuration
public class ConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    private ConsumerFactory<String, Object> consumerFactory(String groupId) {
        Map<String, Object> props = new HashMap<>();

        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG, groupId);

        JsonDeserializer<Object> jsonDeserializer = new JsonDeserializer<>();
        jsonDeserializer.addTrustedPackages("*");

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), jsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> errorCommonHandlingKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory("errorHandlingConsumerGroup"));
        // SeekToCurrentErrorHandler 의 경우, 현재 읽은 오프셋에서 에러가 발생하면 FixedBackOff 등으로 설정한 backoff 만큼 기다리다가 다시 메시지를 읽는다.
        // FixedBackOff(주기(밀리세컨), 최대재시도횟수) 로 백오프를 지정했다.
        factory.setCommonErrorHandler(new DefaultErrorHandler(new FixedBackOff(100, 2)));
        factory.setConcurrency(1);
        factory.setAutoStartup(true);
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> errorHandlingKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory("errorHandlingConsumerGroup"));
        factory.setCommonErrorHandler(new CommonErrorHandler() {
            @Override
            public boolean handleOne(Exception thrownException, ConsumerRecord<?, ?> record, Consumer<?, ?> consumer, MessageListenerContainer container) {
                log.error("Error is {} : data {}", thrownException.getMessage(), record);
                return false;
            }
        });
        factory.setConcurrency(1);
        factory.setAutoStartup(true);
        return factory;
    }

//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, Object> recoveryHandlingKafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory("retryHandlingConsumerGroup"));
//        factory.setRetryTemplate(retryTemplate());
//        factory.setRecoveryCallback(new RecoveryCallback<Object>() {
//            @Override
//            public Object recover(RetryContext retryContext) throws Exception {
//                ConsumerRecord consumerRecord = (ConsumerRecord) retryContext.getAttribute("record");
//                log.info("Recovery is called for message {} ", consumerRecord.value());
//                return null;
//            }
//        });
//        factory.setConcurrency(1);
//        factory.setAutoStartup(true);
//        return factory;
//    }
//
//    private RetryTemplate retryTemplate() {
//        RetryTemplate retryTemplate = new RetryTemplate();
//        retryTemplate.setRetryPolicy(getSimpleRetryPolicy());
//        return retryTemplate;
//    }
//
//    private SimpleRetryPolicy getSimpleRetryPolicy() {
//        Map<Class<? extends Throwable>, Boolean> exceptionMap = new HashMap<>();
//        exceptionMap.put(RetryTestException.class, true);
//        return new SimpleRetryPolicy(5,exceptionMap,true);
//    }
}
