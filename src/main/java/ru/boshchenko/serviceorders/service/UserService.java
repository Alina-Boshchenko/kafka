package ru.boshchenko.serviceorders.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.boshchenko.serviceorders.dto.UserDto;
import ru.boshchenko.serviceorders.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    Page<User> getAll(Pageable pageable);

    User getOne(UUID id);

    List<User> getMany(List<UUID> ids);

    User create(UserDto userDto);

    User patch(UUID id, JsonNode patchNode);

    User delete(UUID id);

}
