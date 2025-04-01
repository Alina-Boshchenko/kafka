package ru.boshchenko.serviceshipping.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.boshchenko.serviceshipping.dto.PaymentEvent;
import ru.boshchenko.serviceshipping.dto.ShippingEvent;
import ru.boshchenko.serviceshipping.mapper.EventMapper;
import ru.boshchenko.serviceshipping.model.ShippingOrder;
import ru.boshchenko.serviceshipping.model.ShippingStatus;
import ru.boshchenko.serviceshipping.repo.ShippingOrderRepository;
import ru.boshchenko.serviceshipping.service.DeliveryService;
import ru.boshchenko.serviceshipping.service.ShippingOrderService;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class ShippingOrderServiceImpl implements ShippingOrderService {

    private final ShippingOrderRepository shippingOrderRepo;

    private final DeliveryService deliveryService;

    private final KafkaTemplate<UUID, ShippingEvent> kafkaTemplate;

    private final EventMapper eventMapper;


    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void createBasedOnAnEvent(PaymentEvent paymentEvent) {
        // сбор товара
        List<String> listCollectedProduct = paymentEvent.getProductId().stream()
                .map(id -> id+" packed")
                .toList();
        // передача в доставку -> возврат трек-номера
        String trackingNumber = deliveryService.sendingForDelivery(listCollectedProduct);

        ShippingOrder shippingOrder = new ShippingOrder();
        shippingOrder.setOrderId(paymentEvent.getOrderId());
        shippingOrder.setTrackingNumber(trackingNumber);
        shippingOrder.setShippingAddress(paymentEvent.getDeliveryAddress());
        shippingOrder.setStatus(ShippingStatus.SHIPPED);

        shippingOrderRepo.save(shippingOrder);

        ShippingEvent event = eventMapper.toShippingEvent(shippingOrder, paymentEvent.getUserId());
        kafkaTemplate.send(
                "sent_orders",
                event.getOrderId(),
                event
        );
        log.info("Создано сообщение: {}", event);
    }

}
