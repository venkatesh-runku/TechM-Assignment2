package com.example.attendance.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String defaultRedirect(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            if (authentication.getAuthorities().stream()
                    .anyMatch(auth -> auth.getAuthority().equals("ROLE_MANAGER"))) {
                return "redirect:/report";  // Manager dashboard
            } else if (authentication.getAuthorities().stream()
                    .anyMatch(auth -> auth.getAuthority().equals("ROLE_EMPLOYEE"))) {
                return "redirect:/mark";    // Employee attendance marking
            }
        }
        return "redirect:/home"; // If not authenticated or unknown role
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("message", "Welcome to the Attendance Management System");
        return "home";  // Maps to home.html or home.jsp depending on your template engine
    }
}
