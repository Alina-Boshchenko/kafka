package ru.boshchenko.serviceorders.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.Map;

@Configuration
@Slf4j
public class KafkaConfigTopic {

    @Bean
    public NewTopic createTopic(){
        log.info("СОЗДАН ТОПИК 'new_orders'");
        return TopicBuilder.name("new_orders")
                .partitions(3)
                .replicas(3)
                .configs(Map.of("min.insync.replicas", "2"))
                .build();
    }
}
