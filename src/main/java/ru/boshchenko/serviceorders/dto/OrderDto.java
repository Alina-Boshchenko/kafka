package ru.boshchenko.serviceorders.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.*;
import lombok.Value;
import ru.boshchenko.serviceorders.model.StatusType;

import java.math.BigDecimal;
import java.util.List;

@Value
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderDto {

    @NotEmpty(message = "list product_id is required")
    List<String> productId;

    @NotNull(message = "status is required")
    StatusType status;

    @NotNull(message = "amount is required")
    @DecimalMin(value = "0.01", message = "amount must be at least 0.01")
    @Digits(integer = 10, fraction = 2, message = "invalid amount format")
    BigDecimal amount;

    @NotNull(message = "delivery_address is required")
    @Pattern(regexp = "^[а-яА-Я\\s\\d,-.]+$", message = "Invalid address format")
    String deliveryAddress;

}