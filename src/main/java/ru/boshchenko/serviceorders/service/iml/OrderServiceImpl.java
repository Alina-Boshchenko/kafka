package ru.boshchenko.serviceorders.service.iml;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.boshchenko.serviceorders.dto.OrderDto;
import ru.boshchenko.serviceorders.dto.OrderEvent;
import ru.boshchenko.serviceorders.mapping.OrderMapping;
import ru.boshchenko.serviceorders.model.Order;
import ru.boshchenko.serviceorders.model.User;
import ru.boshchenko.serviceorders.repo.OrderRepository;
import ru.boshchenko.serviceorders.service.OrderService;
import ru.boshchenko.serviceorders.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepo;

    private final UserService userService;

    private final ObjectMapper objectMapper;

    private final OrderMapping orderMapping;

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Order create(UUID userId, OrderDto orderDto) {
        User user = userService.getOne(userId);

        Order order = orderMapping.toOrder(orderDto);
        order.setUser(user);

        Order sevedOrder = orderRepo.save(order);

        OrderEvent event = orderMapping.toOrderEvent(sevedOrder);

        kafkaTemplate.send(
                "new_orders",
                event.getOrderId().toString(),
                event
        );
        log.info("Создано сообщение: {}", event);

        return sevedOrder;
    }

    @Override
    public Page<Order> getAll(Pageable pageable) {
        return orderRepo.findAll(pageable);
    }

    @Override
    public Order getOne(UUID id) {
        Optional<Order> orderOptional = orderRepo.findById(id);
        return orderOptional.orElseThrow(() ->
                new ResourceNotFoundException("Entity with id `%s` not found".formatted(id)));
    }

    @Override
    public List<Order> getMany(List<UUID> ids) {
        return orderRepo.findAllById(ids);
    }


    @SneakyThrows
    @Override
    public Order patch(UUID id, JsonNode patchNode) {
        Order order = orderRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Entity with id `%s` not found".formatted(id)));

        objectMapper.readerForUpdating(order).readValue(patchNode);

        return orderRepo.save(order);
    }

    @Override
    public Order delete(UUID id) {
        Order order = orderRepo.findById(id).orElse(null);
        if (order != null) {
            orderRepo.delete(order);
        }
        return order;
    }



}
