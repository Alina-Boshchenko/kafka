package ru.boshchenko.serviceshipping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShippingEvent {
    private UUID orderId;
    private UUID userId;
    private String trackingNumber;
    private Instant timestamp;
}
