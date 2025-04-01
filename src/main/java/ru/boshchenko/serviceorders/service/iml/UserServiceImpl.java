package ru.boshchenko.serviceorders.service.iml;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.boshchenko.serviceorders.dto.UserDto;
import ru.boshchenko.serviceorders.model.User;
import ru.boshchenko.serviceorders.repo.UserRepository;
import ru.boshchenko.serviceorders.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    private final ObjectMapper objectMapper;


    @Override
    public Page<User> getAll(Pageable pageable) {
        return userRepo.findAll(pageable);
    }

    @Override
    public User getOne(UUID id) {
        Optional<User> userOptional = userRepo.findById(id);
        return userOptional.orElseThrow(() ->
                new ResourceNotFoundException("Entity with id `%s` not found".formatted(id)));
    }

    @Override
    public List<User> getMany(List<UUID> ids) {
        return userRepo.findAllById(ids);
    }

    @Override
    public User create(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPhone(userDto.getPhone());
        user.setCardNumber(userDto.getCardNumber());
        return userRepo.save(user);
    }

    @SneakyThrows
    @Override
    public User patch(UUID id, JsonNode patchNode) {
        User user = userRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Entity with id `%s` not found".formatted(id)));

        objectMapper.readerForUpdating(user).readValue(patchNode);

        return userRepo.save(user);
    }

    @Override
    public User delete(UUID id) {
        User user = userRepo.findById(id).orElse(null);
        if (user != null) {
            userRepo.delete(user);
        }
        return user;
    }

}
