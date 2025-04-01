package ru.boshchenko.servicenotification.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.boshchenko.servicenotification.service.PhoneSendingService;

@Service
@Slf4j
public class PhoneSendingServiceImpl implements PhoneSendingService {

    @Override
    public void sendingAnPhone(String phone, String text) {
        log.info("Уведомление {} отправлено на номер телефона {}", text,phone);
    }
}
