package ru.boshchenko.serviceorders.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Value;

@Value
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserDto {

    @NotBlank(message = "username is required")
    String username;

    @Pattern(regexp = "^(\\+7|8)?[9]\\d{9}$", message = "incorrect phone number")
    @NotBlank(message = "phone is required")
    String phone;

    @NotBlank(message = "card_number is required")
    String cardNumber;

}