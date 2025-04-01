package ru.boshchenko.serviceshipping.service;

import ru.boshchenko.serviceshipping.dto.PaymentEvent;

public interface ShippingOrderService {

    public void createBasedOnAnEvent(PaymentEvent paymentEvent);

}
