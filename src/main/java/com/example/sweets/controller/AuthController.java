package com.example.sweets.controller;

import com.example.sweets.dto.auth.LoginResponseDto;
import com.example.sweets.dto.auth.MeResponseDto;
import com.example.sweets.dto.request.LoginDto;

import com.example.sweets.dto.response.user.UserResponseDto;
import com.example.sweets.entity.user.User;
import com.example.sweets.repository.UserRepository;
import com.example.sweets.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/auth/v1")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestParam String username,
                                                  @RequestParam String password) {

        return ResponseEntity.ok(authService.login(username, password));
    }

    @GetMapping("/me")
    public MeResponseDto getCurrentUser(Authentication authentication){
        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return new MeResponseDto(user.getUsername(), user.getEmail(), roles);
    }

}
