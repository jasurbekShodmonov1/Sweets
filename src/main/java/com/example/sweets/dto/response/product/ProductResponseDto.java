package com.example.sweets.dto.response.product;

import com.example.sweets.entity.user.User;

import java.util.UUID;

public record ProductResponseDto(
        UUID id,
        String name,
        String photoUrl,
        Long price,
        int count,
        User user
) {


}
