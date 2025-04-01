package ru.boshchenko.serviceorders.controller.rest;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.boshchenko.serviceorders.dto.OrderDto;
import ru.boshchenko.serviceorders.model.Order;
import ru.boshchenko.serviceorders.service.OrderService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<Page<Order>> getAll(@PageableDefault(sort = "id") Pageable pageable) {
        return ResponseEntity.ok(orderService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOne(@PathVariable UUID id) {
        return ResponseEntity.ok(orderService.getOne(id));
    }

    @GetMapping("/by-ids")
    public ResponseEntity<List<Order>> getMany(@RequestParam List<UUID> ids) {
        return ResponseEntity.ok(orderService.getMany(ids));
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestParam UUID userId, @Valid @RequestBody OrderDto order) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.create(userId, order));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Order> patch(@PathVariable UUID id, @RequestBody JsonNode patchNode) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(orderService.patch(id, patchNode));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Order> delete(@PathVariable UUID id) {
        return ResponseEntity.ok(orderService.delete(id));
    }

}
