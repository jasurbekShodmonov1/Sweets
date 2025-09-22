package com.example.sweets.controller.product;

import com.example.sweets.dto.request.product.ProductRequestDto;
import com.example.sweets.dto.response.product.ProductResponseDto;
import com.example.sweets.service.product.Productservice;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/product/v1")
@RequiredArgsConstructor
public class ProductController {

  private final Productservice productservice;

  @GetMapping()
  public ResponseEntity<List<ProductResponseDto>> getAll(){
      List<ProductResponseDto> productResponseDtos = productservice.getAll();

      return ResponseEntity.ok(productResponseDtos);
  }
  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<ProductResponseDto> createProduct(
      @Valid @ModelAttribute ProductRequestDto productRequestDto) throws Exception {
    ProductResponseDto product = productservice.createProduct(productRequestDto);

    return ResponseEntity.ok(product);
  }

  @GetMapping("/myProducts")
  public ResponseEntity<List<ProductResponseDto>> getMyProducts(){
      List<ProductResponseDto> productResponseDtos = productservice.getMyProducts();

      return ResponseEntity.ok(productResponseDtos);
  }

  @GetMapping("/{username}")
  public ResponseEntity<List<ProductResponseDto>> getByUsername(@PathVariable String username){
      List<ProductResponseDto> productResponseDtos = productservice.getByUsername(username);
      return ResponseEntity.ok(productResponseDtos);
  }

  @DeleteMapping("/{productId}")
   public boolean deleteProduct(@PathVariable("productId")UUID id){
      return productservice.deleteProduct(id);
  }

  @PutMapping(value = "/{productId}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductResponseDto> updateProduct (@PathVariable("productId") UUID id,
    @Valid @ModelAttribute ProductRequestDto productRequestDto) throws Exception{
      ProductResponseDto product = productservice.updateProduct(id, productRequestDto);
      return ResponseEntity.ok(product);
  }
}
