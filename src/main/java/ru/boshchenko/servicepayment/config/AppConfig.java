package ru.boshchenko.servicepayment.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public NewTopic newPayedOrders() {
        return TopicBuilder.name("payed_orders")
                .partitions(3)
                .replicas(3)
                .compact()
                .build();
    }
}
