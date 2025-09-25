package com.example.sweets.controller.order;


import com.example.sweets.dto.response.order.OrderResponseDto;
import com.example.sweets.entity.order.OrderStatus;
import com.example.sweets.service.order.OrderService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders/v1")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/place")
    public OrderResponseDto placeOrder(Authentication authentication){
        String username = authentication.getName();
        return orderService.placeOrder(username);
    }

    @GetMapping("/myOrders")
    public List<OrderResponseDto> getMyOrders(Authentication authentication){
        String username = authentication.getName();
        return orderService.getUserOrders(username);
    }

    @PutMapping("/{orderId}/status")
    public OrderResponseDto updateStatus(@PathVariable UUID orderId,
                                         @RequestParam OrderStatus status) {
        return orderService.changeStatus(orderId, status);
    }
}
