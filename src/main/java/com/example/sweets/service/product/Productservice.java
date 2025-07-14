package com.example.sweets.service.product;


import com.example.sweets.dto.request.product.ProductRequestDto;
import com.example.sweets.dto.response.product.ProductResponseDto;
import com.example.sweets.entity.product.Product;
import com.example.sweets.entity.user.User;
import com.example.sweets.mapper.product.ProductMapper;
import com.example.sweets.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Productservice {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final UserDetails userDetails;

    public ProductResponseDto createProduct(ProductRequestDto productRequestDto){
        Product product = productMapper.toEntity(productRequestDto);
        User user = userDetails.
    }

    public User getCurrentuser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails
    }
}
