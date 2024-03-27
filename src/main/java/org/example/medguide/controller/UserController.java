package org.example.medguide.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.medguide.model.Role;
import org.example.medguide.model.User;
import org.example.medguide.service.RoleService;
import org.example.medguide.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    @GetMapping
    public String showUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users/users";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        List<Role> roles = roleService.getRoles();
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "users/user-form";
    }

    @PostMapping("/save")
    public String saveUser(User user, RedirectAttributes redirectAttributes, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", roleService.getRoles());
            return "users/user-form";
        }
        if (!userService.isEmailUnique(user.getEmail())) {
            redirectAttributes.addFlashAttribute("message1", "User with email " + user.getEmail() + " already exists!");
            return "redirect:/users";
        }
        if (user.getRoles().isEmpty()) {
            redirectAttributes.addFlashAttribute("message3", "You have forgotten to assign a role for the user you were trying to add!");
            return "redirect:/users";
        }
        userService.addUser(user);
        redirectAttributes.addFlashAttribute("message2", "The user has been added successfully.");
        return "redirect:/users";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam(name = "userId") Integer id, RedirectAttributes redirectAttributes) {
        User user = userService.findUserById(id);
        userService.deleteUser(id);
        redirectAttributes.addFlashAttribute("message", "User " + user.getFirstName() + " " + user.getLastName()
        + " (" + user.getEmail() + ") has been deleted successfully.");
        return "redirect:/users";
    }
}
