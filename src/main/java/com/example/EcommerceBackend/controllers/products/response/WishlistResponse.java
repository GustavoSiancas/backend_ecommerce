package com.example.EcommerceBackend.controllers.products.response;

public record WishlistResponse(
    Long id,
    Boolean isActive,
    ProductsResponse product
) {    
}
