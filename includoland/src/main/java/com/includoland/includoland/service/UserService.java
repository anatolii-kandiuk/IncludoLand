package com.includoland.includoland.service;

import com.includoland.includoland.model.entity.User;

public interface UserService {
    User getUserById(String uuid);

    User getUserByEmail(String email);
}
