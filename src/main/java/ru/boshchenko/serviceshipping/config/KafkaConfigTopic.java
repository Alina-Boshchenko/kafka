package ru.boshchenko.serviceshipping.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.Map;

@Configuration
public class KafkaConfigTopic {

    @Value("${spring.kafka.topics.sent_orders.name}")
    private String topicName;

    @Value("${spring.kafka.topics.sent_orders.partitions}")
    private int partitions;

    @Value("${spring.kafka.topics.sent_orders.replication-factor}")
    private int replicas;

    @Bean
    public NewTopic newSentOrders() {
        return TopicBuilder.name(topicName)
                .partitions(partitions)
                .replicas(replicas)
                .configs(Map.of("min.insync.replicas", "2"))
                .build();
    }
}
