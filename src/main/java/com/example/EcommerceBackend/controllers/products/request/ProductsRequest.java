package com.example.EcommerceBackend.controllers.products.request;

import java.math.BigDecimal;

import com.example.EcommerceBackend.entities.enums.Category;

public record ProductsRequest(
    Long userId,
    String name,
    BigDecimal price,
    String imageUrl,
    String description,
    Integer stock,
    Category category
) {
    
}
