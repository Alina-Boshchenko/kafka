package ru.boshchenko.servicepayment.service.iml;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.boshchenko.serviceorders.dto.OrderEvent;
import ru.boshchenko.servicepayment.service.OrderEventConsumer;
import ru.boshchenko.servicepayment.service.PaymentService;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderEventConsumerImpl implements OrderEventConsumer {

    private final PaymentService paymentService;

    @Override
    @KafkaListener(
            topics = "${spring.kafka.topics.new_orders.name}",
            groupId = "payment-group",
            concurrency = "${spring.kafka.topics.new_orders.partitions}"
    )
    public void handleOrder(OrderEvent orderEvent) {
        try {
            log.info("Получено сообщение: {}", orderEvent);
            paymentService.create(orderEvent);
            log.info("Обработано сообщение: {}", orderEvent);
        } catch (Exception e) {
            log.error("Ошибка обработки сообщения: {}", e.getMessage(), e);
        }
    }

}
