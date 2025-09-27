package com.example.EcommerceBackend.controllers.users.request;

import java.time.LocalDate;

import com.example.EcommerceBackend.entities.enums.UserType;

public record RegisterRequest(
    String email,
    String password,
    String names,
    String lastname,
    String phone,
    String dni,
    LocalDate birthDate,
    String photo,
    UserType userType
) {
    
}
