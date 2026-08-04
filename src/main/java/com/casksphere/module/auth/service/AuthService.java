package com.casksphere.module.auth.service;

import com.casksphere.exception.DuplicateResourceException;
import com.casksphere.exception.ResourceNotFoundException;
import com.casksphere.module.auth.dto.*;
import com.casksphere.module.auth.entity.RefreshToken;
import com.casksphere.module.auth.entity.Role;
import com.casksphere.module.auth.entity.User;
import com.casksphere.module.auth.repository.RefreshTokenRepository;
import com.casksphere.module.auth.repository.RoleRepository;
import com.casksphere.module.auth.repository.UserRepository;
import com.casksphere.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateResourceException("Username already exists: " + request.getUsername());
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email already exists: " + request.getEmail());
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(passwordEncoder.encode(request.getPassword()))
                .enabled(true)
                .build();

        Role corporateUserRole = roleRepository.findByName("CORPORATE_USER")
                .orElseThrow(() -> new ResourceNotFoundException("CORPORATE_USER role not found"));

        Set<Role> roles = new HashSet<>();
        roles.add(corporateUserRole);
        user.setRoles(roles);

        user = userRepository.save(user);
        log.info("User registered successfully: {}", user.getUsername());

        return generateAuthResponse(user);
    }

    @Transactional
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + request.getEmail()));

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);

        log.info("User logged in successfully: {}", user.getUsername());
        return generateAuthResponse(user);
    }

    @Transactional
    public AuthResponse refreshAccessToken(RefreshTokenRequest request) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(request.getRefreshToken())
                .orElseThrow(() -> new ResourceNotFoundException("Refresh token not found"));

        if (refreshToken.isRevoked() || refreshToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new ResourceNotFoundException("Refresh token is expired or revoked");
        }

        User user = refreshToken.getUser();
        String newAccessToken = jwtProvider.generateAccessToken(user.getUsername(), user.getId());

        log.debug("Access token refreshed for user: {}", user.getUsername());

        return AuthResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(request.getRefreshToken())
                .tokenType("Bearer")
                .expiresIn(jwtExpiration / 1000)
                .user(mapUserToDto(user))
                .build();
    }

    @Transactional
    public void logout(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + username));

        refreshTokenRepository.deleteByUser(user);
        log.info("User logged out successfully: {}", username);
    }

    @Transactional(readOnly = true)
    public UserDto getProfile(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + username));
        return mapUserToDto(user);
    }

    private AuthResponse generateAuthResponse(User user) {
        String accessToken = jwtProvider.generateAccessToken(user.getUsername(), user.getId());
        String refreshToken = jwtProvider.generateRefreshToken(user.getUsername(), user.getId());

        RefreshToken refreshTokenEntity = RefreshToken.builder()
                .user(user)
                .token(refreshToken)
                .expiryDate(LocalDateTime.now().plusWeeks(1))
                .build();
        refreshTokenRepository.save(refreshTokenEntity);

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType("Bearer")
                .expiresIn(jwtExpiration / 1000)
                .user(mapUserToDto(user))
                .build();
    }

    private UserDto mapUserToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .enabled(user.isEnabled())
                .roles(user.getRoles().stream()
                        .map(Role::getName)
                        .collect(Collectors.toSet()))
                .createdAt(user.getCreatedAt())
                .lastLogin(user.getLastLogin())
                .build();
    }
}
