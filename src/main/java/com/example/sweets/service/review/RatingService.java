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
import java.util.ArrayList;
import java.util.List;

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
        ratingRepository.save(rating);

        product.getRatings().add(rating);
        Double avgRating = calculateAverageRating(product);
        product.setAverageRating(avgRating);
        productRepository.save(product);

        return ratingMapper.toDto(rating);
    }

    private Double calculateAverageRating(Product product){

        int sum=0;

        for (Rating r : product.getRatings()) {
            switch (r.getEnumRating()) {
                case ONE -> sum += 1;
                case TWO -> sum += 2;
                case THREE -> sum += 3;
                case FOUR -> sum += 4;
                case FIVE -> sum += 5;
            }
        }
        System.out.println(sum);

        return (double) sum / product.getRatings().size();
    }

}
