package com.example.sweets.entity.product;

import com.example.sweets.entity.base.BaseDomain;
import com.example.sweets.entity.user.User;
import jakarta.persistence.*;

import java.util.UUID;

@Entity

public class Product extends BaseDomain<UUID> {

    @Column(unique = true)
    private String name;

    private String photoUrl;
    private Long price;
    private int count;
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User currentUser;

}
