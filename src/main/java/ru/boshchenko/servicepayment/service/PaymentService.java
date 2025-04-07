package ru.boshchenko.servicepayment.service;

//import ru.boshchenko.servicepayment.dto.OrderEvent;

import ru.boshchenko.serviceorders.dto.OrderEvent;

public interface PaymentService {

    void create(OrderEvent orderEvent);

}
