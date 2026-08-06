package com.cashsphere.module.auth.controller;

import com.cashsphere.module.auth.dto.*;
import com.cashsphere.module.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "User authentication and authorization endpoints")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        AuthResponse response = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    @Operation(summary = "Login with email and password")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh-token")
    @Operation(summary = "Refresh access token")
    public ResponseEntity<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        AuthResponse response = authService.refreshAccessToken(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Logout user")
    public ResponseEntity<Void> logout(Authentication authentication) {
        authService.logout(authentication.getName());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/profile")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Get current user profile")
    public ResponseEntity<UserDto> getProfile(Authentication authentication) {
        UserDto userDto = authService.getProfile(authentication.getName());
        return ResponseEntity.ok(userDto);
    }
}
