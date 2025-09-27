package com.example.EcommerceBackend.controllers.users.response;

import java.time.LocalDate;

import com.example.EcommerceBackend.entities.enums.UserType;

public record LoginResponse(
    String token,
    Long id,
    String email,
    String names,
    String lastname,
    String phone,
    String dni,
    LocalDate birthDate,
    String photo,
    UserType userType
) {
    
}
