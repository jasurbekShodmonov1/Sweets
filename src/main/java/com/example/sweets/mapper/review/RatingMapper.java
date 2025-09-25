package com.example.sweets.mapper.review;


import com.example.sweets.dto.response.review.RatingResponseDto;
import com.example.sweets.entity.review.Rating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RatingMapper {

    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "product.id", target = "productId")
    RatingResponseDto toDto(Rating rating);

    List<RatingResponseDto> toRatingDtoList(List<Rating> entities);
}
