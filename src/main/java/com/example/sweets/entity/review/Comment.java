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
@Table(name = "comments")
@Getter
@Setter
public class Comment extends BaseDomain<UUID> {
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(length = 1000)
    private String text;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
