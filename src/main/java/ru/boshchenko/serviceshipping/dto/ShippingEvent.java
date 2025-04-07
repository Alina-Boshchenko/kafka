package ru.boshchenko.serviceshipping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShippingEvent {
    private String orderId;
    private String userId;
    private String trackingNumber;
    private Instant timestamp;
}
