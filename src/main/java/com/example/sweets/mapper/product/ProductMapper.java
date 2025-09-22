package com.example.sweets.mapper.product;

import com.example.sweets.dto.request.product.ProductRequestDto;
import com.example.sweets.dto.request.user.RoleRequestDto;
import com.example.sweets.dto.response.product.ProductResponseDto;
import com.example.sweets.entity.product.Product;
import com.example.sweets.entity.user.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  @Mapping(target = "id", ignore = true)

  @Mapping(target = "photoUrl", ignore = true)

  Product toEntity(ProductRequestDto productRequestDto);

  @Mapping(target = "photoUrl", source = "photoUrl")
  @Mapping(source = "currentUser.username", target = "createdBy")
  ProductResponseDto toDto(Product product);

  @Mapping(target = "id", ignore = true)
  void updateFromDto(ProductRequestDto productRequestDto, @MappingTarget Product product);
}
