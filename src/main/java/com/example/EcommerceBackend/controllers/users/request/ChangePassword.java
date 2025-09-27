package com.example.EcommerceBackend.controllers.users.request;

public record ChangePassword(
    Long id,
    String oldPassword,
    String newPassword
) {
    
}
