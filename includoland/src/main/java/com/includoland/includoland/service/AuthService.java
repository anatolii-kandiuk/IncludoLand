package com.includoland.includoland.service;

import com.includoland.includoland.dto.auth.AuthResponse;
import com.includoland.includoland.dto.auth.LoginRequest;
import com.includoland.includoland.dto.auth.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
