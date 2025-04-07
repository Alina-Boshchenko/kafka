package ru.boshchenko.servicenotification.service;

import org.springframework.stereotype.Service;
import ru.boshchenko.serviceshipping.dto.ShippingEvent;

@Service
public interface NotificationService {
    void createNotificationOnAnEvent(ShippingEvent shippingEvent);
}
