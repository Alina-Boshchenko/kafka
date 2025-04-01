package ru.boshchenko.servicenotification.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ru.boshchenko.servicenotification.dto.ShippingEvent;
import ru.boshchenko.servicenotification.dto.UserResponse;
import ru.boshchenko.servicenotification.service.PhoneSendingService;
import ru.boshchenko.servicenotification.service.NotificationService;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final PhoneSendingService phoneSendingService;
    private final RestTemplate restTemplate;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void createNotificationOnAnEvent(ShippingEvent shippingEvent) {
        String url = "http://orders-service:8080/api/user/{id}";
        UserResponse userResponse = restTemplate.getForObject(url, UserResponse.class, shippingEvent.getUserId());
        if (userResponse==null){
            log.error("Ошибка запроса по адресу {} юзер с id {} не найден", url,shippingEvent.getUserId());
            throw new ResourceNotFoundException("User is required");
        }

        phoneSendingService.sendingAnPhone(userResponse.getPhone(),String.format("Уважаемый клиент, %s, ваш заказ номер %s, доставлен",
                userResponse.getUsername(),shippingEvent.getTrackingNumber()));
    }
}
