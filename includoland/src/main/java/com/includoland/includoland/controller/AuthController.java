package com.includoland.includoland.controller;

import com.includoland.includoland.model.dto.auth.AuthResponse;
import com.includoland.includoland.model.dto.auth.LoginRequest;
import com.includoland.includoland.model.dto.auth.RegisterRequest;
import com.includoland.includoland.model.entity.User;
import com.includoland.includoland.model.enums.Role;
import com.includoland.includoland.repository.UserRepository;
import com.includoland.includoland.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return "redirect:/register?error";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPremium(false);
        user.setRole(user.getRole() == null ? Role.CHILD : user.getRole());
        userRepository.save(user);
        return "redirect:/login?success";
    }

    @RestController
    @RequestMapping("/api/v1/auth")
    @RequiredArgsConstructor
    static class ApiAuthController {
        private final AuthService authService;

        @PostMapping("/register")
        public ResponseEntity<AuthResponse> registerApi(@RequestBody RegisterRequest request) {
            return ResponseEntity.ok(authService.register(request));
        }

        @PostMapping("/login")
        public ResponseEntity<AuthResponse> loginApi(@RequestBody LoginRequest request) {
            return ResponseEntity.ok(authService.login(request));
        }
    }
}
