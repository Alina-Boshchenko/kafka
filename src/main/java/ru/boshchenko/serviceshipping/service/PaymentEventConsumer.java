package ru.boshchenko.serviceshipping.service;

import org.springframework.stereotype.Service;
import ru.boshchenko.serviceshipping.dto.PaymentEvent;

@Service
public interface PaymentEventConsumer {
    void handlePayment(PaymentEvent paymentEvent);
}
