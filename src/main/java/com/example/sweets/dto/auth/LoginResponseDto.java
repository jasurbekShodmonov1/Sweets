package com.example.sweets.dto.auth;

import java.util.List;

public record LoginResponseDto(
        String token,
        String username,
        List<String> roles
) {
}
