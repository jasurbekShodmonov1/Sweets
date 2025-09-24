package com.example.sweets.dto.response.cart;

import java.util.List;
import java.util.UUID;

public record CartResponseDto(
        UUID id,
        String username,
        List<CartItemResponseDto> items
) {
}
