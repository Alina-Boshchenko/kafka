package ru.boshchenko.serviceshipping.mapper;

import org.springframework.stereotype.Component;
import ru.boshchenko.serviceshipping.dto.ShippingEvent;
import ru.boshchenko.serviceshipping.model.ShippingOrder;

import java.time.Instant;
import java.util.UUID;

@Component
public class EventMapper {

    public ShippingEvent toShippingEvent(ShippingOrder shippingOrder, UUID userId){
        return new ShippingEvent(
                shippingOrder.getOrderId().toString(),
                userId.toString(),
                shippingOrder.getTrackingNumber(),
                Instant.now()
        );
    }

}
