package de.unibayreuth.se.taskboard.business.ports;

import de.unibayreuth.se.taskboard.business.domain.User;
import de.unibayreuth.se.taskboard.business.exceptions.DuplicateNameException;
import de.unibayreuth.se.taskboard.business.exceptions.UserNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    void clearUsers();
    List<User> getAllUsers();
    Optional<User> getUserById(UUID id);
    User upsertUser(User user) throws UserNotFoundException, DuplicateNameException;
}
