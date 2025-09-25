package com.example.sweets.entity.product;

import com.example.sweets.entity.base.BaseDomain;
import com.example.sweets.entity.review.Comment;
import com.example.sweets.entity.review.Rating;
import com.example.sweets.entity.user.User;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product extends BaseDomain<UUID> {

  @Column(unique = true)
  private String name;

  @Column(length = 2048)
  private String photoUrl;

  private BigDecimal price;
  private Integer count;


  @Column(columnDefinition = "TEXT")
  private String description;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id", nullable = false)
  private User currentUser;

  private Double averageRating;

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  private List<Comment> comments = new ArrayList<>();
}
