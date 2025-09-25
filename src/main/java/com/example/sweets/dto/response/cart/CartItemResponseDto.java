package com.example.sweets.dto.response.cart;

import java.util.UUID;

public record CartItemResponseDto(
        UUID productId,
        String productName,
        Integer quantity,
        Long price
) {
}
