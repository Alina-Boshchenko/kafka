package ru.boshchenko.serviceshipping.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeliveryService {
    String sendingForDelivery(List<String> products);
}
