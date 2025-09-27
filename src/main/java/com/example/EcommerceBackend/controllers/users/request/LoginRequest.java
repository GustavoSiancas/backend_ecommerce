package com.example.EcommerceBackend.controllers.users.request;

public record LoginRequest(
    String email,
    String password
) {
    
}
