package de.unibayreuth.se.taskboard.business.impl;

import de.unibayreuth.se.taskboard.business.domain.User;
import de.unibayreuth.se.taskboard.business.exceptions.DuplicateNameException;
import de.unibayreuth.se.taskboard.business.exceptions.UserNotFoundException;
import de.unibayreuth.se.taskboard.business.ports.UserPersistenceService;
import de.unibayreuth.se.taskboard.business.ports.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserPersistenceService userPersistenceService;

    // The upsert should throw the Exception!

    public UserServiceImpl(UserPersistenceService userPersistenceService) {
        this.userPersistenceService = userPersistenceService;
    }

    @Override
    public void clearUsers() {
        userPersistenceService.clear();
    }

    @Override
    public List<User> getAllUsers() {
        return userPersistenceService.getAll();
    }

    @Override
    public Optional<User> getUserById(UUID id) {
        return userPersistenceService.getById(id);
    }

    @Override
    public User upsertUser(User user) throws UserNotFoundException, DuplicateNameException {
        return userPersistenceService.upsert(user);
    }
}
