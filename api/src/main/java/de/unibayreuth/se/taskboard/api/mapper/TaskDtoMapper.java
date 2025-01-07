package de.unibayreuth.se.taskboard.api.mapper;

import de.unibayreuth.se.taskboard.api.dtos.TaskDto;
import de.unibayreuth.se.taskboard.api.dtos.UserDto;
import de.unibayreuth.se.taskboard.business.domain.Task;
import de.unibayreuth.se.taskboard.business.ports.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Mapper(componentModel = "spring")
public abstract class TaskDtoMapper {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDtoMapper userDtoMapper;

    protected boolean utcNowUpdated = false;
    protected LocalDateTime utcNow;

    @Mapping(target = "assignee", expression = "java(getUserById(source.getAssigneeId()))")
    public abstract TaskDto fromBusiness(Task source);

    @Mapping(target = "assigneeId", source = "assignee.id")
    @Mapping(target = "status", source = "status", defaultValue = "TODO")
    @Mapping(target = "createdAt", expression = "java(mapTimestamp(source.getCreatedAt()))")
    @Mapping(target = "updatedAt", expression = "java(mapTimestamp(source.getUpdatedAt()))")
    public abstract Task toBusiness(TaskDto source);

    protected UserDto getUserById(UUID userId) {
        if (userId == null) {
            return null;
        }
        return userDtoMapper.toDto(userService.getUserById(userId));
    }

    protected LocalDateTime mapTimestamp(LocalDateTime timestamp) {
        if (timestamp == null) {
            if (!utcNowUpdated) {
                utcNow = LocalDateTime.now(ZoneId.of("UTC"));
                utcNowUpdated = true;
            } else {
                utcNowUpdated = false;
            }
            return utcNow;
        }
        return timestamp;
    }
}