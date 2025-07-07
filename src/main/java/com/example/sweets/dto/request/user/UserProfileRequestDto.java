package com.example.sweets.dto.request.user;

import java.util.List;

public record UserProfileRequestDto(
        String username,
        String password,

        String fullName,
        String email,
        String phone,

        List<String> roles
){}
