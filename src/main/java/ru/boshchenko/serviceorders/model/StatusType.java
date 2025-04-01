package ru.boshchenko.serviceorders.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusType {
    PENDING("СОЗДАН"), PROCESSING("ОПЛАЧЕН"), CANCELED("ОТМЕНЕН"),
    COMPLETED("СОБРАН"), DELIVERED("ДОСТАВЛЕН");

    private final String value;
}
