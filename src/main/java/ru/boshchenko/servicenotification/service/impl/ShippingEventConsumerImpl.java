package ru.boshchenko.servicenotification.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.boshchenko.serviceshipping.dto.ShippingEvent;
import ru.boshchenko.servicenotification.service.NotificationService;
import ru.boshchenko.servicenotification.service.ShippingEventConsumer;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShippingEventConsumerImpl implements ShippingEventConsumer {

    private final NotificationService notificationService;

    @Override
    @KafkaListener(
            topics = "${spring.kafka.topics.sent_orders.name}",
            groupId = "notification-group",
            concurrency = "${spring.kafka.topics.sent_orders.partitions}"
    )
    public void handleShipping(ShippingEvent shippingEvent) {
        try {
            log.info("Получено сообщение: {}", shippingEvent);
            notificationService.createNotificationOnAnEvent(shippingEvent);
            log.info("Обработано сообщение: {}", shippingEvent);
        } catch (Exception e) {
            log.error("Ошибка обработки сообщения: {}", e.getMessage(), e);
        }
    }
}
