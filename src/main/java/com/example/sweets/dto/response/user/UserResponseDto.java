package com.example.sweets.dto.response.user;

import com.example.sweets.dto.response.product.ProductResponseDto;
import java.util.List;
import java.util.UUID;

public record UserResponseDto(
    UUID id,
    String username,
    String fullName,
    String email,
    String phoneNumber,
    List<RoleResponseDto> roles
    ) {}
