package com.example.sweets.dto.auth;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record TokenInfoResponse(
    UUID userId, String token, List<String> authorities, String username) {}
