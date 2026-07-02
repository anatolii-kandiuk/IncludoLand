package com.includoland.includoland.controller;

import com.includoland.includoland.model.entity.User;
import com.includoland.includoland.model.enums.Role;
import com.includoland.includoland.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DashboardController {
    private final UserService userService;

    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByEmail(userDetails.getUsername());
        return user.getRole() == Role.SPECIALIST ? "redirect:/specialist/dashboard" : "redirect:/child/dashboard";
    }

    @GetMapping("/specialist/dashboard")
    public String specialistDashboard(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userService.getUserByEmail(userDetails.getUsername());
        model.addAttribute("user", user);
        model.addAttribute("username", user.getFirstName());
        return "specialist/specialist_dashboard";
    }

    @GetMapping("/child/dashboard")
    public String childDashboard(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userService.getUserByEmail(userDetails.getUsername());
        model.addAttribute("user", user);
        model.addAttribute("username", user.getFirstName());
        return "child/child_dashboard";
    }
}
