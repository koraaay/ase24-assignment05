package de.unibayreuth.se.taskboard.api.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserDto(
        // @Nullable
        UUID id,
        // @Nullable
        LocalDateTime createdAt,
        // @NotNull
        // @Pattern(regexp ="//w)
        String name
) { }
