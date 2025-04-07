package ru.boshchenko.serviceorders.mapping;

import org.springframework.stereotype.Component;
import ru.boshchenko.serviceorders.dto.OrderDto;
import ru.boshchenko.serviceorders.dto.OrderEvent;
import ru.boshchenko.serviceorders.model.Order;

@Component
public class OrderMapping {

    public Order toOrder(OrderDto orderDto){
        Order order = new Order();
        order.setProductId(orderDto.getProductId());
        order.setStatus(orderDto.getStatus());
        order.setAmount(orderDto.getAmount());
        order.setDeliveryAddress(orderDto.getDeliveryAddress());
        return order;
    }

    public OrderEvent toOrderEvent(Order order){
        return new OrderEvent(
                order.getId().toString(),
                order.getUser().getId().toString(),
                order.getAmount(),
                order.getProductId(),
                order.getDeliveryAddress()
        );
    }

}
