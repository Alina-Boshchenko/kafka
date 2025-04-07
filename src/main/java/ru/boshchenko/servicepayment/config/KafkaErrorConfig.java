package ru.boshchenko.servicepayment.config;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.BackOffHandler;
import org.springframework.kafka.listener.ConsumerRecordRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
@Slf4j
public class KafkaErrorConfig {

    @Bean
    public DefaultErrorHandler errorHandler() {
        FixedBackOff backOff = new FixedBackOff(3000, 3);

        ConsumerRecordRecoverer recoverer = (record, ex) -> {
            log.error("Окончательный сбой после повторных попыток. Топик: {}, раздел: {}, ключ: {}, значение: {}",
                    record.topic(), record.partition(), record.key(), record.value(), ex);
        };

        BackOffHandler backOffHandler = new BackOffHandler() {
            @Override
            public void onNextBackOff(MessageListenerContainer container, Exception exception, long nextBackOff) {
                log.warn("Повторите попытку через {} мс для обнаружения ошибки: {}", nextBackOff,exception.getMessage());
            }
        };

        DefaultErrorHandler errorHandler = new DefaultErrorHandler(recoverer,backOff,backOffHandler);

        errorHandler.addNotRetryableExceptions(
                ConstraintViolationException.class,
                IllegalArgumentException.class
        );

        return errorHandler;
    }

}
