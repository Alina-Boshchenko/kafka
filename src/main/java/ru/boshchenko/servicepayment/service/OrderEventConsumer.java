package ru.boshchenko.servicepayment.service;

import org.springframework.stereotype.Service;
import ru.boshchenko.serviceorders.dto.OrderEvent;
//import ru.boshchenko.servicepayment.dto.OrderEvent;

@Service
public interface OrderEventConsumer {
    void handleOrder(OrderEvent orderEvent);
}
