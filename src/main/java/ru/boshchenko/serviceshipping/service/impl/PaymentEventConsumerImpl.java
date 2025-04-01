package ru.boshchenko.serviceshipping.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.boshchenko.serviceshipping.dto.PaymentEvent;
import ru.boshchenko.serviceshipping.service.PaymentEventConsumer;
import ru.boshchenko.serviceshipping.service.ShippingOrderService;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentEventConsumerImpl implements PaymentEventConsumer {

    private final ShippingOrderService service;

    @Override
    @KafkaListener(topics = "payed_orders", groupId = "payment-group")
    public void handlePayment(PaymentEvent paymentEvent) {
        service.createBasedOnAnEvent(paymentEvent);
        log.info("Принято сообщение в сервисе отгрузка {}", paymentEvent);
    }
}
