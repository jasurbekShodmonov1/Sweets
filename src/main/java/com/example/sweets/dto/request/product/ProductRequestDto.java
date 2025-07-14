package com.example.sweets.dto.request.product;

import java.util.UUID;

public record ProductRequestDto (
    UUID ID,
    String name,
    String photoUrl,
    Long price,
    int count,
    String description
){
}
