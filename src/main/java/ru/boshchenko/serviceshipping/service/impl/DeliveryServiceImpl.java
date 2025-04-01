package ru.boshchenko.serviceshipping.service.impl;

import org.springframework.stereotype.Service;
import ru.boshchenko.serviceshipping.service.DeliveryService;

import java.util.List;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Override
    public String sendingForDelivery(List<String> products) {
        return "RQ4848468";
    }
}
