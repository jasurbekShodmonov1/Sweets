package com.example.sweets.service.auth;

import com.example.sweets.dto.auth.LoginResponseDto;
import com.example.sweets.entity.user.User;
import com.example.sweets.repository.UserRepository;
import com.example.sweets.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public LoginResponseDto  login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.isEnabled()) {
            throw new RuntimeException("Account not verified. Please check your email.");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getUsername(),user.getRoles());

        return new LoginResponseDto(
                token,
                user.getUsername(),
                user.getRoles().stream().map(r -> r.getName()).toList()
        );
    }
}
