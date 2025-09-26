package com.example.sweets.dto.response.product;

import com.example.sweets.dto.response.review.CommentResponseDto;
import com.example.sweets.dto.response.review.RatingResponseDto;
import com.example.sweets.entity.user.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record ProductResponseDto(
        UUID id,
        String name,
        String photoUrl,
        BigDecimal price,
        Integer count,
        Double averageRating,
        String createdBy) {}
