package ru.boshchenko.servicepayment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEvent {
    private UUID orderId;
    private UUID userId;
    private List<String> productId;
    private String deliveryAddress;
}
