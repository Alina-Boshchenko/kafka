package ru.boshchenko.serviceorders.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import lombok.Value;
import ru.boshchenko.serviceorders.model.StatusType;

@Value
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderUpdate {

    @NotNull(message = "status is required")
    StatusType status;

}