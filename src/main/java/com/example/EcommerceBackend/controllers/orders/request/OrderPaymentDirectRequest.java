package com.example.EcommerceBackend.controllers.orders.request;

public record OrderPaymentDirectRequest(
    Long userId,
    OrderItemRequest orderItemRequest
) {    
}
