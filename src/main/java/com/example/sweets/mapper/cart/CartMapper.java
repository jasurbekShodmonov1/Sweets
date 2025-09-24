package com.example.sweets.mapper.cart;

import com.example.sweets.dto.response.cart.CartItemResponseDto;
import com.example.sweets.dto.response.cart.CartResponseDto;
import com.example.sweets.entity.cart.Cart;
import com.example.sweets.entity.cart.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {

    @Mapping(source = "user.username", target = "username")
    CartResponseDto toDto(Cart cart);

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    @Mapping(source = "product.price", target = "price")
    CartItemResponseDto toDto(CartItem cartItem);
}
