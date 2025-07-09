package com.elearning.userservice.service;

import com.elearning.userservice.dto.AuthResponse;
import com.elearning.userservice.dto.LoginRequest;
import com.elearning.userservice.exception.InvalidCredentialsException;
import com.elearning.userservice.model.User;
import com.elearning.userservice.repository.UserRepository;
import com.elearning.userservice.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthResponse authenticate(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid credentials"));

        String token = jwtService.generateToken(user);

        return AuthResponse.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole().name())
                .token(token)
                .expiresAt(LocalDateTime.now().plusHours(24))
                .build();
    }
}