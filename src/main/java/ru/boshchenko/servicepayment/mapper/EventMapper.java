package ru.boshchenko.servicepayment.mapper;

import org.springframework.stereotype.Component;
import ru.boshchenko.servicepayment.dto.OrderEvent;
import ru.boshchenko.servicepayment.dto.PaymentEvent;
import ru.boshchenko.servicepayment.model.Payment;

@Component
public class EventMapper {

    public Payment toPayment(OrderEvent orderEvent){
        Payment payment = new Payment();
        payment.setOrderId(orderEvent.getOrderId());
        payment.setUserId(orderEvent.getUserId());
        payment.setAmount(orderEvent.getAmount());
        return payment;
    }

    public PaymentEvent toPaymentEvent(OrderEvent orderEvent){
        return new PaymentEvent(
                orderEvent.getOrderId(),
                orderEvent.getUserId(),
                orderEvent.getProductId(),
                orderEvent.getDeliveryAddress());
    }
}
