package com.example.sweets.controller;


import com.example.sweets.config.CurrentUser;
import com.example.sweets.dto.auth.TokenInfoResponse;
import com.example.sweets.dto.auth.UserDtoResponse;
import com.example.sweets.dto.request.LoginDto;
import com.example.sweets.dto.response.user.UserResponseDto;
import com.example.sweets.entity.user.User;
import com.example.sweets.mapper.UserMapper;
import com.example.sweets.service.auth.AuthTokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth/v1")
@RequiredArgsConstructor

public class AuthController {
    private final AuthTokenService authTokenService;
    private final UserMapper userMapper;

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getCurrentUser(@CurrentUser User user) {
        log.info(user.toString());
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenInfoResponse> login(@Valid @RequestBody LoginDto loginDto) {
        System.out.println(loginDto.username());
        var token = authTokenService.generateToken(loginDto);
        System.out.println(token);
        return ResponseEntity.ok(token);
    }
}
