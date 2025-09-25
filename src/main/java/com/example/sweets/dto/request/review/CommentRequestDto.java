package com.example.sweets.dto.request.review;

import com.example.sweets.entity.review.Rating;

import java.util.UUID;

public record CommentRequestDto(
        UUID productId,
        Rating rating
) {
}
