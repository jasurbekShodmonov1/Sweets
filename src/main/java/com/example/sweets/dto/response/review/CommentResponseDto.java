package com.example.sweets.dto.response.review;

import java.time.LocalDateTime;
import java.util.UUID;

public record CommentResponseDto(
        UUID id,
        String username,
        UUID productId,
        String text,
        LocalDateTime createdAt
) {
}
