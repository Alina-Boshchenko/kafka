package ru.boshchenko.serviceorders.controller.rest;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.boshchenko.serviceorders.dto.UserDto;
import ru.boshchenko.serviceorders.model.User;
import ru.boshchenko.serviceorders.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<Page<User>> getAll(Pageable pageable) {
        return ResponseEntity.ok(userService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getOne(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getOne(id));
    }

    @GetMapping("/by-ids")
    public ResponseEntity<List<User>> getMany(@RequestParam List<UUID> ids) {
        return ResponseEntity.ok(userService.getMany(ids));
    }

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody UserDto user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(user));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> patch(@PathVariable UUID id, @RequestBody JsonNode patchNode) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.patch(id, patchNode));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.delete(id));
    }

}
