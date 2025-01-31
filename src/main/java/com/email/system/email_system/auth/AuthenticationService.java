package com.email.system.email_system.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.email.system.email_system.config.JwtService;
import com.email.system.email_system.model.*;
import com.email.system.email_system.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;

        public AuthenticationResponse registerUser(RegisterRequest request) {
                User user = User.builder()
                                .firstName(request.getFirstName())
                                .lastName(request.getLastName())
                                .email(request.getEmail())
                                .password(passwordEncoder.encode(request.getPassword()))
                                .build();
                userRepository.save(user);
                var jwtToken = jwtService.generateToken(user);
                return AuthenticationResponse.builder().token(jwtToken).build();
        }

        public AuthenticationResponse authenticate(AuthenticationRequest request) {
                // Authenticate credentials using the authentication manager
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

                // Try to find the user in the UserRepository
                var user = userRepository.findByEmail(request.getEmail()).orElse(null);
                if (user != null) {
                        // Generate JWT token for the user
                        var jwtToken = jwtService.generateToken(user);
                        return AuthenticationResponse.builder()
                                        .token(jwtToken)
                                        .build();
                }
                throw new UsernameNotFoundException("User not found with email: " + request.getEmail());
        }
} 
