package com.example.sweets.dto.auth;

import java.util.List;
import java.util.UUID;
import lombok.Builder;

@Builder
public record TokenInfoResponse(
    UUID userId, String token, List<String> authorities, String username) {}
