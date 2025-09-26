package com.example.sweets.dto.request.review;

import com.example.sweets.entity.review.EnumRating;

import java.util.UUID;

public record RatingRequestDto(
        UUID productId,
        EnumRating enumRating
) {}
