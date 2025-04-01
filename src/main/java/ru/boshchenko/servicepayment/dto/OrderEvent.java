package ru.boshchenko.servicepayment.dto;

import jakarta.validation.constraints.*;
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

//    @NotNull(message = "order_id is required")
    private UUID orderId;

//    @NotNull(message = "user_id is required")
    private UUID userId;

//    @NotNull(message = "amount is required")
//    @DecimalMin(value = "0.01", message = "amount must be at least 0.01")
//    @Digits(integer = 10, fraction = 2, message = "invalid amount format")
    private BigDecimal amount;

//    @NotEmpty(message = "list products_id is required")
    private List<String> productId;

//    @NotBlank(message = "delivery_address is required")
//    @Pattern(regexp = "^[а-яА-Я\\s\\d,-]+$", message = "Invalid address format")
    private String deliveryAddress;
}
