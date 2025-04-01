package ru.boshchenko.servicepayment.service.iml;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.boshchenko.servicepayment.dto.OrderEvent;
import ru.boshchenko.servicepayment.service.OrderEventConsumer;
import ru.boshchenko.servicepayment.service.PaymentService;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderEventConsumerImpl implements OrderEventConsumer {

    private final PaymentService paymentService;

    @Override
    @KafkaListener(topics = "new_orders", groupId = "orders-group")
    public void handleOrder(OrderEvent orderEvent){
        paymentService.create(
               orderEvent
        );
        log.info("Принято сообщение в сервисе оплаты {}", orderEvent);
    }

}
