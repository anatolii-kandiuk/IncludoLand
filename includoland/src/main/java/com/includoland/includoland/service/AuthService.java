package com.includoland.includoland.service;

import com.includoland.includoland.model.dto.auth.AuthResponse;
import com.includoland.includoland.model.dto.auth.LoginRequest;
import com.includoland.includoland.model.dto.auth.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
