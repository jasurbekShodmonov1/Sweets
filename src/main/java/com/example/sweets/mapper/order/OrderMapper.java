package com.example.sweets.mapper.order;


import com.example.sweets.dto.response.order.OrderItemResponseDto;
import com.example.sweets.dto.response.order.OrderResponseDto;
import com.example.sweets.entity.order.Order;
import com.example.sweets.entity.order.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    OrderItemResponseDto toItemDto(OrderItem orderItem);

    List<OrderItemResponseDto> toItemDtos(List<OrderItem> items);

    @Mapping(source = "items", target = "items")
    OrderResponseDto toDto(Order order);
}
