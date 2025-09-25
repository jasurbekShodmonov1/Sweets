package com.example.sweets.entity.cart;

import com.example.sweets.entity.base.BaseDomain;
import com.example.sweets.entity.product.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Entity
@Getter
@Setter
public class CartItem extends BaseDomain<UUID> {

    @ManyToOne
    private Cart cart;

    @ManyToOne
    private Product product;

    private Integer quantity;
}
