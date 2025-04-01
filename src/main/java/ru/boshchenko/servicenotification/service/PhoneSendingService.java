package ru.boshchenko.servicenotification.service;

import org.springframework.stereotype.Service;

@Service
public interface PhoneSendingService {

    void sendingAnPhone(String phone, String text);

}
