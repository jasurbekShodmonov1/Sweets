package com.example.sweets.controller.review;


import com.example.sweets.dto.request.review.RatingRequestDto;
import com.example.sweets.dto.response.review.RatingResponseDto;
import com.example.sweets.entity.review.EnumRating;
import com.example.sweets.service.review.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/rating/v1")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;
    @PostMapping()
    public RatingResponseDto addRating(Authentication authentication, @RequestBody RatingRequestDto ratingRequestDto){
        String username = authentication.getName();
        return ratingService.addRating(username, ratingRequestDto);
    }

    @PutMapping("/{ratingId}")
    public RatingResponseDto updateRating(Authentication authentication,
                                          @PathVariable UUID ratingId,
                                          @RequestParam EnumRating enumRating){
        String username = authentication.getName();
        return ratingService.updateRating(username,ratingId,enumRating);
    }
}
