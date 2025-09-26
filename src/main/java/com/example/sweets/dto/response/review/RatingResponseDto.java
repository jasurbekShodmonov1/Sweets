package com.example.sweets.dto.response.review;

import com.example.sweets.entity.review.EnumRating;

import java.time.LocalDateTime;
import java.util.UUID;

public record RatingResponseDto(
        UUID id,
        String username,
        UUID productId,
        EnumRating enumRating,
        LocalDateTime createdAt
) {
}
