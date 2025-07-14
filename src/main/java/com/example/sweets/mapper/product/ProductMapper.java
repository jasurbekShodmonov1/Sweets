package com.example.sweets.mapper.product;

import com.example.sweets.dto.request.product.ProductRequestDto;
import com.example.sweets.dto.response.product.ProductResponseDto;
import com.example.sweets.entity.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id",  ignore = true)
    Product toEntity(ProductRequestDto productRequestDto);

    ProductResponseDto toDto(Product product);
}
