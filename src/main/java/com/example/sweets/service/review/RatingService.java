package com.example.sweets.service.review;


import com.example.sweets.dto.request.review.RatingRequestDto;
import com.example.sweets.dto.response.review.RatingResponseDto;
import com.example.sweets.entity.product.Product;
import com.example.sweets.entity.review.Rating;
import com.example.sweets.entity.user.User;
import com.example.sweets.mapper.review.RatingMapper;
import com.example.sweets.repository.ProductRepository;
import com.example.sweets.repository.UserRepository;
import com.example.sweets.repository.review.RatingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final RatingMapper ratingMapper;

    @Transactional
    public RatingResponseDto addRating(String username, RatingRequestDto ratingRequestDto){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findById(ratingRequestDto.productId())
                .orElseThrow(()->new RuntimeException("Product not found"));

        ratingRepository.findByUserAndProduct(user,product)
                .ifPresent(r -> {
                    throw new RuntimeException("User already rated this product");
                });

        Rating rating = new Rating();
        rating.setUser(user);
        rating.setProduct(product);
        rating.setEnumRating(ratingRequestDto.enumRating());
        rating.setCreatedAt(LocalDateTime.now());

        Double avg = ratingRepository.findAverageRatingByProductId(product.getId());
        product.setAverageRating(avg);
        productRepository.save(product);

        Rating saved =  ratingRepository.save(rating);
        return ratingMapper.toDto(saved);
    }

}
