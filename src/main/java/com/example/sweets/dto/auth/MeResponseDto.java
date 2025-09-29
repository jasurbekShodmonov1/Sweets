package com.example.sweets.dto.auth;

import java.util.List;

public record MeResponseDto(String username,
                            String email,
                            List<String> roles) {
}
