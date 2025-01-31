package com.email.system.email_system.controller;

import com.email.system.email_system.dto.UserDTO;
import com.email.system.email_system.model.User;
import com.email.system.email_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Get user by email
    @GetMapping("/{email}")
    public Optional<UserDTO> getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    // Register a new user
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        if (userService.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("User already exists with this email");
        }
        return userService.saveUser(user);
    }
}
