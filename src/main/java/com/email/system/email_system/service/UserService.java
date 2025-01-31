package com.email.system.email_system.service;

import com.email.system.email_system.dto.DTOMapper;
import com.email.system.email_system.dto.UserDTO;
import com.email.system.email_system.model.User;
import com.email.system.email_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Find user by email
    public Optional<UserDTO> getUserByEmail(String email) {
        return userRepository.findByEmail(email).map(DTOMapper::toUserDTO);
    }

    // Save a new user
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // Check if a user exists by email
    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
