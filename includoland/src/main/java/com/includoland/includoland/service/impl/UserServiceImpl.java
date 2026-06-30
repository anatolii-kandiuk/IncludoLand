package com.includoland.includoland.service.impl;

import com.includoland.includoland.model.entity.User;
import com.includoland.includoland.repository.UserRepository;
import com.includoland.includoland.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User getUserById(String uuid) {
        UUID id;
        try {
            id = UUID.fromString(uuid);
        } catch (IllegalArgumentException ex) {
            throw new EntityNotFoundException("Invalid UUID format: " + uuid);
        }

        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + uuid));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
    }
}
