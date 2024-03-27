package org.example.medguide.controller;

import lombok.RequiredArgsConstructor;
import org.example.medguide.model.User;
import org.example.medguide.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final UserService userService;

    @GetMapping("/")
    public String viewHomePage(Principal principal, Model model) {
        String email = principal.getName();
        User authenticatedUser = userService.findUserByEmail(email);
        model.addAttribute("authenticatedUser", authenticatedUser);
        return "home";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
}
