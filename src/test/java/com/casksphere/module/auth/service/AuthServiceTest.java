package com.cashsphere.module.auth.service;

import com.cashsphere.exception.DuplicateResourceException;
import com.cashsphere.exception.ResourceNotFoundException;
import com.cashsphere.module.auth.dto.LoginRequest;
import com.cashsphere.module.auth.dto.RegisterRequest;
import com.cashsphere.module.auth.entity.Role;
import com.cashsphere.module.auth.entity.User;
import com.cashsphere.module.auth.repository.RefreshTokenRepository;
import com.cashsphere.module.auth.repository.RoleRepository;
import com.cashsphere.module.auth.repository.UserRepository;
import com.cashsphere.security.JwtProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private RefreshTokenRepository refreshTokenRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtProvider jwtProvider;

    @InjectMocks
    private AuthService authService;

    private RegisterRequest registerRequest;
    private User user;
    private Role corporateUserRole;

    @BeforeEach
    void setUp() {
        registerRequest = new RegisterRequest();
        registerRequest.setUsername("testuser");
        registerRequest.setEmail("test@example.com");
        registerRequest.setPassword("password123");
        registerRequest.setFirstName("Test");
        registerRequest.setLastName("User");

        corporateUserRole = Role.builder()
                .id(1L)
                .name("CORPORATE_USER")
                .description("Corporate user role")
                .build();

        user = User.builder()
                .id(1L)
                .username("testuser")
                .email("test@example.com")
                .password("encodedPassword")
                .firstName("Test")
                .lastName("User")
                .enabled(true)
                .roles(Set.of(corporateUserRole))
                .build();
    }

    @Test
    void testRegisterSuccess() {
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(roleRepository.findByName("CORPORATE_USER")).thenReturn(Optional.of(corporateUserRole));
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(jwtProvider.generateAccessToken(anyString(), anyLong())).thenReturn("accessToken");
        when(jwtProvider.generateRefreshToken(anyString(), anyLong())).thenReturn("refreshToken");

        var response = authService.register(registerRequest);

        assertNotNull(response);
        assertEquals("accessToken", response.getAccessToken());
        assertEquals("refreshToken", response.getRefreshToken());
        assertEquals("Bearer", response.getTokenType());
        verify(userRepository, times(1)).save(any(User.class));
        verify(refreshTokenRepository, times(1)).save(any());
    }

    @Test
    void testRegisterDuplicateUsername() {
        when(userRepository.existsByUsername("testuser")).thenReturn(true);

        assertThrows(DuplicateResourceException.class, () -> authService.register(registerRequest));
        verify(userRepository, never()).save(any());
    }

    @Test
    void testRegisterDuplicateEmail() {
        when(userRepository.existsByUsername("testuser")).thenReturn(false);
        when(userRepository.existsByEmail("test@example.com")).thenReturn(true);

        assertThrows(DuplicateResourceException.class, () -> authService.register(registerRequest));
        verify(userRepository, never()).save(any());
    }

    @Test
    void testLoginSuccess() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("password123");

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));
        when(authenticationManager.authenticate(any())).thenReturn(new UsernamePasswordAuthenticationToken("testuser", "password123"));
        when(jwtProvider.generateAccessToken(anyString(), anyLong())).thenReturn("accessToken");
        when(jwtProvider.generateRefreshToken(anyString(), anyLong())).thenReturn("refreshToken");

        var response = authService.login(loginRequest);

        assertNotNull(response);
        assertEquals("accessToken", response.getAccessToken());
        verify(userRepository, times(2)).findByEmail("test@example.com");
    }

    @Test
    void testLoginUserNotFound() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("nonexistent@example.com");
        loginRequest.setPassword("password123");

        when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> authService.login(loginRequest));
    }

    @Test
    void testLogout() {
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));

        authService.logout("testuser");

        verify(refreshTokenRepository, times(1)).deleteByUser(user);
    }

    @Test
    void testGetProfileSuccess() {
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));

        var userDto = authService.getProfile("testuser");

        assertNotNull(userDto);
        assertEquals("testuser", userDto.getUsername());
        assertEquals("test@example.com", userDto.getEmail());
    }

    @Test
    void testGetProfileUserNotFound() {
        when(userRepository.findByUsername("nonexistent")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> authService.getProfile("nonexistent"));
    }
}
