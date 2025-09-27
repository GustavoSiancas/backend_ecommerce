package com.example.EcommerceBackend.controllers.products.request;

public record AddWishList(
    Long userId,
    Long productId
) {    
}
