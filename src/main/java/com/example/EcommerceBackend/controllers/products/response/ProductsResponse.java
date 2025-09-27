package com.example.EcommerceBackend.controllers.products.response;

import com.example.EcommerceBackend.entities.enums.Category;
import com.example.EcommerceBackend.entities.enums.Status;

import java.math.BigDecimal;

public record ProductsResponse (
    Long id,
    String name,
    BigDecimal price,
    String imageUrl,
    Double rating,
    String description,
    Integer stock,
    Status status,
    Category category
){
    
    
}
