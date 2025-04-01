package ru.boshchenko.servicenotification.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.boshchenko.servicenotification.dto.ShippingEvent;
import ru.boshchenko.servicenotification.service.NotificationService;
import ru.boshchenko.servicenotification.service.ShippingEventConsumer;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShippingEventConsumerImpl implements ShippingEventConsumer {

    private final NotificationService notificationService;

    @Override
    @KafkaListener(topics = "sent_orders", groupId = "shipping-group")
    public void handleShipping(ShippingEvent shippingEvent) {
        notificationService.createNotificationOnAnEvent(shippingEvent);
        log.info("Принято сообщение в сервисе уведомлений {}", shippingEvent);
    }
}
