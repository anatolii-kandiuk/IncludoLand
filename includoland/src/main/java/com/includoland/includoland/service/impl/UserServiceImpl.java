package com.includoland.includoland.service.impl;

import com.includoland.includoland.model.entity.User;
import com.includoland.includoland.repository.UserRepository;
import com.includoland.includoland.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User getUserById(String uuid) {
        return userRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + uuid));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
    }
}
