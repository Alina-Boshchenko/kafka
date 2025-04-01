package ru.boshchenko.serviceorders.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.boshchenko.serviceorders.dto.OrderDto;
import ru.boshchenko.serviceorders.model.Order;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface OrderService {
    Page<Order> getAll(Pageable pageable);

    Order getOne(UUID id);

    List<Order> getMany(List<UUID> ids);

    Order create(UUID userId, OrderDto orderDto);

    Order patch(UUID id, JsonNode patchNode);

    Order delete(UUID id);
}
