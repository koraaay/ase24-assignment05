package de.unibayreuth.se.taskboard.api.controller;

import de.unibayreuth.se.taskboard.api.dtos.UserDto;
import de.unibayreuth.se.taskboard.api.mapper.UserDtoMapper;
import de.unibayreuth.se.taskboard.business.ports.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@OpenAPIDefinition(
        info = @Info(
                title = "TaskBoard",
                version = "0.0.1"
        )
)
@Tag(name = "Users")
@Controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    @GetMapping
    public List<UserDto> getAllUsers() {
            return userDtoMapper.toDtoList(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable UUID id) {
            return userDtoMapper.toDto(userService.getUserById(id));
    }

    @PostMapping
    public void createUser(@RequestBody UserDto userDto) {
            userService.upsertUser(userDtoMapper.toEntity(userDto));
    }
}
