package com.example.sweets.mapper.review;


import com.example.sweets.dto.response.review.CommentResponseDto;
import com.example.sweets.dto.response.review.RatingResponseDto;
import com.example.sweets.entity.review.Comment;
import com.example.sweets.entity.review.Rating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "product.id", target = "productId")
    CommentResponseDto toDto(Comment comment);
}
