package com.example.sweets.dto.response.user;

import java.util.UUID;

public record RoleResponseDto(
        UUID id,
        String name,
        String description
) {
}
