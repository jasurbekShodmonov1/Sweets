package com.example.sweets.dto.request.user;

import com.example.sweets.entity.user.Role;

import java.util.List;
import java.util.UUID;

public record UserRequestDto(
        String username,
        String fullName,
        String email,
        String phoneNumber,
        String password,
        List<UUID> roleIds
){}
