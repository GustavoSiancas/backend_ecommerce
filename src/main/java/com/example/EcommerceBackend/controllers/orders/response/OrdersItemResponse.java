package com.example.EcommerceBackend.controllers.orders.response;

import java.math.BigDecimal;

import com.example.EcommerceBackend.controllers.products.response.ProductsResponse;

public record OrdersItemResponse(
    ProductsResponse product,
    Integer quantity,
    BigDecimal price
) {
} 
