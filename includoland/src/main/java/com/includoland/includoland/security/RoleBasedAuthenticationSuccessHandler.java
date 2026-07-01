package com.includoland.includoland.security;

import com.includoland.includoland.model.entity.User;
import com.includoland.includoland.model.enums.Role;
import com.includoland.includoland.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class RoleBasedAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElse(null);

        if (user != null && user.getRole() == Role.SPECIALIST) {
            response.sendRedirect(request.getContextPath() + "/specialist/dashboard");
            return;
        }

        response.sendRedirect(request.getContextPath() + "/child/dashboard");
    }
}
