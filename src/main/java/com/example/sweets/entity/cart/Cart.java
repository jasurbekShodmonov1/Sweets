package com.example.sweets.entity.cart;

import com.example.sweets.entity.base.BaseDomain;
import com.example.sweets.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Getter
@Setter
public class Cart extends BaseDomain<UUID> {


    @OneToOne
    private User user;

    @OneToMany(mappedBy = "cart",fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();
}
