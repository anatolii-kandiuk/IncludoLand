package com.includoland.includoland.model.dto.auth;

import com.includoland.includoland.model.enums.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
}
