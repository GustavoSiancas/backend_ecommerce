package com.example.EcommerceBackend.controllers.orders.request;

public record AddCart(
    Long userId,
    Long productId,
    Integer quantity
) {
    
}
