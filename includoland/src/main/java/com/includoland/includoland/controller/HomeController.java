package com.includoland.includoland.controller;

import com.includoland.includoland.model.entity.User;
import com.includoland.includoland.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final UserService userService;

    @GetMapping("/")
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !"anonymousUser".equals(authentication.getPrincipal())) {
            String email = authentication.getName();
            try {
                User user = userService.getUserByEmail(email);
                model.addAttribute("username", user.getFirstName());
            } catch (EntityNotFoundException ex) {
                model.addAttribute("username", email);
            }
        }
        return "index";
    }
}
