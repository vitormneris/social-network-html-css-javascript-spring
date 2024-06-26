package com.socialnetwork.socialnetwork.services;

import com.socialnetwork.socialnetwork.infrastructure.entities.UserEntity;
import com.socialnetwork.socialnetwork.infrastructure.repositories.UserRepository;
import com.socialnetwork.socialnetwork.services.exceptions.DatabaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CheckDatabaseService {
    private final UserRepository userRepository;

    public void usernameExists(String username) throws DatabaseException {
        Optional<UserEntity> obj = userRepository.findByUsername(username);

        if (obj.isPresent())
            throw new DatabaseException("Username already exists");
    }

    public void emailExists(String email) throws DatabaseException {
        Optional<UserEntity> obj = userRepository.findByEmail(email);

        if (obj.isPresent())
            throw new DatabaseException("Email already exists");
    }
}
