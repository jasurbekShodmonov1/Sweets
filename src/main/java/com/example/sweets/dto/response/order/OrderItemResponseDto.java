package com.example.sweets.dto.response.order;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItemResponseDto(
        UUID productId,
        String productName,
        int quantity,
        BigDecimal price
) {
}
