package com.example.sweets.repository;

import com.example.sweets.entity.product.Product;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findByCurrentUserUsername(String username);
}
