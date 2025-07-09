package com.elearning.userservice.controller;

import com.elearning.userservice.dto.*;
import com.elearning.userservice.model.User;
import com.elearning.userservice.service.AuthService;
import com.elearning.userservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.registerUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/trainers")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> addTrainer(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.addTrainer(request));
    }

    @GetMapping("/trainers")
    public ResponseEntity<List<UserResponse>> getAllTrainers() {
        return ResponseEntity.ok(userService.getAllTrainers());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}