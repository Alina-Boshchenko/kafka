package ru.boshchenko.servicepayment.service.iml;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ru.boshchenko.servicepayment.dto.BankResponse;
import ru.boshchenko.servicepayment.dto.OrderEvent;
import ru.boshchenko.servicepayment.dto.PaymentEvent;
import ru.boshchenko.servicepayment.dto.UserResponse;
import ru.boshchenko.servicepayment.mapper.EventMapper;
import ru.boshchenko.servicepayment.model.Payment;
import ru.boshchenko.servicepayment.model.PaymentStatus;
import ru.boshchenko.servicepayment.repo.PaymentRepository;
import ru.boshchenko.servicepayment.service.BankService;
import ru.boshchenko.servicepayment.service.PaymentService;

import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    private final RestTemplate restTemplate;

    private final KafkaTemplate<String, PaymentEvent> kafkaTemplate;

    private final BankService bankService;

    private final EventMapper mapper;


    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void create(OrderEvent orderEvent) {
        String url = "http://service-orders:8080/api/user/{id}";
        UserResponse userResponse = restTemplate.getForObject(url, UserResponse.class, orderEvent.getUserId());
        if (userResponse==null){
            log.error("Ошибка запроса по адресу {} юзер с id {} не найден", url,orderEvent.getUserId());
            throw new ResourceNotFoundException("User is required");
        }

        Payment payment = mapper.toPayment(orderEvent);
        payment.setStatus(PaymentStatus.CREATED);
        paymentRepository.save(payment);

        // тут логика отправки запроса банку и получаение ответа от него
        BankResponse response = bankService.paymentProcessing(userResponse.getCardNumber());

        if (response.isSuccess()){
            payment.setStatus(PaymentStatus.SUCCESS);
            payment.setBankTransactionId(response.getTransactionId());
            kafkaTemplate.send("payed_orders", orderEvent.getOrderId().toString(), mapper.toPaymentEvent(orderEvent));
            log.info("Оплата прошла успешно, сообщение отправлено в топик payed_orders");
        } else {
            payment.setStatus(PaymentStatus.FAILED);
            log.error("Оплата не прошла");
        }
    }

}
