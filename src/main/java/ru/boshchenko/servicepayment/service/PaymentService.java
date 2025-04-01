package ru.boshchenko.servicepayment.service;

import ru.boshchenko.servicepayment.dto.OrderEvent;

public interface PaymentService {

    void create(OrderEvent orderEvent);

}
