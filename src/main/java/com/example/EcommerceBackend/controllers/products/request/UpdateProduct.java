package com.example.EcommerceBackend.controllers.products.request;

import java.math.BigDecimal;

import com.example.EcommerceBackend.entities.enums.Status;

public record UpdateProduct(
    String name,
    BigDecimal price,
    String imageUrl,
    String description,
    Integer stock,
    Status status
) {
    
}
