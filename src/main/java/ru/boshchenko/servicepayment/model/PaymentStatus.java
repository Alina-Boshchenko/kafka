package ru.boshchenko.servicepayment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PaymentStatus {

    CREATED("Создан"), PROCESSING("Обработка"), SUCCESS("Успех"), FAILED("Провал");
    private final String value;
}
