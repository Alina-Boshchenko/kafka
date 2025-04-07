package ru.boshchenko.serviceshipping.service;

import ru.boshchenko.servicepayment.dto.PaymentEvent;

public interface ShippingOrderService {

    public void createBasedOnAnEvent(PaymentEvent paymentEvent);

}
