package com.example.sweets.entity.review;


import com.example.sweets.entity.base.BaseDomain;
import com.example.sweets.entity.product.Product;
import com.example.sweets.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "rating")
@Getter
@Setter
public class Rating  extends BaseDomain<UUID> {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnumRating enumRating;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
