package com.example.sweets.service.product;

import com.example.sweets.dto.request.product.ProductRequestDto;
import com.example.sweets.dto.response.product.ProductResponseDto;
import com.example.sweets.dto.response.user.RoleResponseDto;
import com.example.sweets.entity.product.Product;
import com.example.sweets.entity.user.User;
import com.example.sweets.mapper.product.ProductMapper;
import com.example.sweets.repository.ProductRepository;
import com.example.sweets.repository.UserRepository;
import com.example.sweets.service.MinioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class Productservice {

  private final ProductRepository productRepository;
  private final UserRepository userRepository;
  private final ProductMapper productMapper;
  private final MinioService minioService;

  public List<ProductResponseDto> getAll(){
      List<Product>  products = productRepository.findAll();
      return products.stream().map(productMapper::toDto).toList();
  }
  public ProductResponseDto createProduct(ProductRequestDto productRequestDto) throws Exception {
    Product product = productMapper.toEntity(productRequestDto);

    if (productRequestDto.photo() != null && !productRequestDto.photo().isEmpty()) {
      String photoUrl = minioService.uploadImage(productRequestDto.photo());
      product.setPhotoUrl(photoUrl);
    }

      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      String username = authentication.getName();
      User currentUser = userRepository.findByUsername(username)
              .orElseThrow(() -> new RuntimeException("User not found"));

      product.setCurrentUser(currentUser);

    Product save = productRepository.save(product);
    return productMapper.toDto(save);
  }

    public List<ProductResponseDto> getMyProducts() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return productRepository.findByCurrentUserUsername(username)
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    public List<ProductResponseDto> getByUsername(String username){
      return productRepository.findByCurrentUserUsername(username)
              .stream()
              .map(productMapper::toDto)
              .toList();
    }

    //updateProduct
    public ProductResponseDto updateProduct(UUID id,ProductRequestDto productRequestDto) throws Exception {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      String username = authentication.getName();
      User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));


      Product product = productRepository.findById(id)
              .orElseThrow(()->new RuntimeException("product not found"));

      if (!product.getCurrentUser().getUsername().equals(currentUser.getUsername())) {
            throw new RuntimeException("You are not allowed to update this product");
      }


      productMapper.updateFromDto(productRequestDto, product);

      if (productRequestDto.photo() != null && !productRequestDto.photo().isEmpty()) {
            String photoUrl = minioService.uploadImage(productRequestDto.photo());
            product.setPhotoUrl(photoUrl);
      }

      Product saved = productRepository.save(product);

      return productMapper.toDto(saved);
    }

    //deleteProduct
    public boolean deleteProduct(UUID id){
      Product product = productRepository.findById(id)
              .orElseThrow(()->new RuntimeException("product not found"));
      productRepository.delete(product);

      return true;
    }
}
