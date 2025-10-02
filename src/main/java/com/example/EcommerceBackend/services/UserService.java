package com.example.EcommerceBackend.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.EcommerceBackend.controllers.users.request.ChangePassword;
import com.example.EcommerceBackend.controllers.users.request.LoginRequest;
import com.example.EcommerceBackend.controllers.users.request.RegisterRequest;
import com.example.EcommerceBackend.controllers.users.response.LoginResponse;
import com.example.EcommerceBackend.entities.User;
import com.example.EcommerceBackend.repositories.UserRepository;

import lombok.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public LoginResponse login(LoginRequest loginRequest){
        User user = userRepository.findByEmail(loginRequest.email()).get();
        if(user != null && loginRequest.password().equals(user.getPassword())){
            return new LoginResponse(
                "token",
                user.getId(),
                user.getEmail(),
                user.getNames(),
                user.getLastname(),
                user.getPhone(),
                user.getDni(),
                user.getBirthDate(),
                user.getPhoto(),
                user.getUserType()
                );
        }
        throw new RuntimeException("Credenciales inválidas");   
    }

    public LoginResponse register(RegisterRequest registerRequest){
        if(userRepository.findByEmail(registerRequest.email()).isPresent()){
            throw new RuntimeException("El email ya está en uso");
        }
        User newUser = new User();
        newUser.setEmail(registerRequest.email());
        newUser.setPassword(registerRequest.password());
        newUser.setNames(registerRequest.names());
        newUser.setLastname(registerRequest.lastname());
        newUser.setPhone(registerRequest.phone());
        newUser.setDni(registerRequest.dni());
        newUser.setBirthDate(registerRequest.birthDate());
        newUser.setPhoto(registerRequest.photo());
        newUser.setUserType(registerRequest.userType());

        userRepository.save(newUser);

        return new LoginResponse(
            "token",
            newUser.getId(),
            newUser.getEmail(),
            newUser.getNames(),
            newUser.getLastname(),
            newUser.getPhone(),
            newUser.getDni(),
            newUser.getBirthDate(),
            newUser.getPhoto(),
            newUser.getUserType()
            );
    }

    public Boolean changePassword(ChangePassword changePassword){
        User user = userRepository.findById(changePassword.id()).get();
        if(passwordEncoder.matches(changePassword.oldPassword(), user.getPassword())){
            user.setPassword(passwordEncoder.encode(changePassword.newPassword()));
            userRepository.save(user); 
            return true;
        
        } else {
            throw new RuntimeException("Contraseña incorrecta");
        }
    }
    
}
