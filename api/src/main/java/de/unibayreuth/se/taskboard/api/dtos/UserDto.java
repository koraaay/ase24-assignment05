package de.unibayreuth.se.taskboard.api.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserDto(
        UUID id,
        LocalDateTime createdAt,
        String name
) { }
