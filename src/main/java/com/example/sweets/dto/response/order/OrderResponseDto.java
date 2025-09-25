package com.example.sweets.dto.response.order;

import com.example.sweets.entity.order.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record OrderResponseDto(
        UUID id,
        LocalDateTime createdAt,
        OrderStatus status,
        BigDecimal totalPrice,
        List<OrderItemResponseDto> items
) {
}
