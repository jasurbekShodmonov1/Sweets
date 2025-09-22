package com.example.sweets.dto.request.product;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record ProductRequestDto(
    String name,
    @NotNull(message = "Photo is required") MultipartFile photo,
    Long price,Integer count,
    String description) {}
