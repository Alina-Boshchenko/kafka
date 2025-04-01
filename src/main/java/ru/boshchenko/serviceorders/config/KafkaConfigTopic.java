package ru.boshchenko.serviceorders.config;

import jakarta.validation.Valid;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfigTopic {

    @Bean
    public NewTopic newOrders(){
        return TopicBuilder.name("new_orders")
                .partitions(3)
                .replicas(3)
                .compact()
                .build();
    }
}
