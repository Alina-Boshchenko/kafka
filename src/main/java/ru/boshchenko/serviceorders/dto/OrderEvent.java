package ru.boshchenko.serviceorders.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEvent {
    private UUID orderId;
    private UUID userId;
    private BigDecimal amount;
    private List<String> productId;
    private String deliveryAddress;
}
