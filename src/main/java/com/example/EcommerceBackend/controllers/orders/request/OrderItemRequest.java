package com.example.EcommerceBackend.controllers.orders.request;

public record OrderItemRequest(
    Long productId,
    Integer quantity
) {    
}
