package com.includoland.includoland.service.impl;

import com.includoland.includoland.dto.auth.AuthResponse;
import com.includoland.includoland.dto.auth.LoginRequest;
import com.includoland.includoland.dto.auth.RegisterRequest;
import com.includoland.includoland.model.entity.User;
import com.includoland.includoland.repository.UserRepository;
import com.includoland.includoland.security.JwtService;
import com.includoland.includoland.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setPremium(false);

        User savedUser = userRepository.save(user);
        String token = jwtService.generateToken(
                org.springframework.security.core.userdetails.User
                        .withUsername(savedUser.getEmail())
                        .password(savedUser.getPassword())
                        .roles(savedUser.getRole().name())
                        .build(),
                savedUser.getRole().name()
        );

        return new AuthResponse(token, savedUser.getRole().name(), savedUser.getUuid());
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        String token = jwtService.generateToken(
                org.springframework.security.core.userdetails.User
                        .withUsername(user.getEmail())
                        .password(user.getPassword())
                        .roles(user.getRole().name())
                        .build(),
                user.getRole().name()
        );

        return new AuthResponse(token, user.getRole().name(), user.getUuid());
    }
}
