package com.example.sweets.repository.review;

import com.example.sweets.entity.product.Product;
import com.example.sweets.entity.review.Rating;
import com.example.sweets.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RatingRepository extends JpaRepository<Rating, UUID> {

    Optional<Rating> findByUserAndProduct(User user, Product product);

    List<Rating> findByProductId(UUID productId);

    @Query(value = "SELECT AVG(CASE " +
            "WHEN r.enum_rating = 'ONE' THEN 1 " +
            "WHEN r.enum_rating = 'TWO' THEN 2 " +
            "WHEN r.enum_rating = 'THREE' THEN 3 " +
            "WHEN r.enum_rating = 'FOUR' THEN 4 " +
            "WHEN r.enum_rating = 'FIVE' THEN 5 END) " +
            "FROM rating r WHERE r.product_id = :productId", nativeQuery = true)
    Double findAverageRatingByProductId(@Param("productId") UUID productId);

}
