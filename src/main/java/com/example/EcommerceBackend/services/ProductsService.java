package com.example.EcommerceBackend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.EcommerceBackend.controllers.products.response.ProductsResponse;
import com.example.EcommerceBackend.repositories.ProductsRepository;
import com.example.EcommerceBackend.repositories.UserRepository;
import com.example.EcommerceBackend.entities.enums.Status;
import com.example.EcommerceBackend.entities.enums.UserType;

import com.example.EcommerceBackend.controllers.products.request.ProductsRequest;
import com.example.EcommerceBackend.controllers.products.request.UpdateProduct;
import com.example.EcommerceBackend.entities.Products;
import com.example.EcommerceBackend.entities.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductsService {
    private final ProductsRepository productsRepository;
    private final UserRepository userRepository;


    public ProductsResponse createProduct(ProductsRequest productsRequest){
        User user = userRepository.findById(productsRequest.userId()).get();
        if (user.getUserType().equals(UserType.CUSTOMER)){
            throw new RuntimeException("Credenciales inválidas"); 
        }

        Products newProduct = new Products();
        newProduct.setUser(user);
        newProduct.setName(productsRequest.name());
        newProduct.setPrice(productsRequest.price());
        newProduct.setImageUrl(productsRequest.imageUrl());
        newProduct.setDescription(productsRequest.description());
        newProduct.setStock(productsRequest.stock());
        newProduct.setCategory(productsRequest.category());
        productsRepository.save(newProduct);
        return new ProductsResponse(
            newProduct.getId(),
            newProduct.getName(),
            newProduct.getPrice(),
            newProduct.getImageUrl(),
            newProduct.getRating(),
            newProduct.getDescription(),
            newProduct.getStock(),
            newProduct.getStatus(),
            newProduct.getCategory()
        );
    }
    
    public List<ProductsResponse> getallActiveProducts(Status status){
        List<Products> products = productsRepository.findByStatus(status);
        return products.stream().map(product -> new ProductsResponse(
            product.getId(),
            product.getName(),
            product.getPrice(),
            product.getImageUrl(),
            product.getRating(),
            product.getDescription(),
            product.getStock(),
            product.getStatus(),
            product.getCategory()
        )).toList();
    }

    public List<ProductsResponse> getAllProductsbyUserId(Long userId){
        List<Products> products = productsRepository.findByUser_Id(userId);

        return products.stream().map(product -> new ProductsResponse(
            product.getId(),
            product.getName(),
            product.getPrice(),
            product.getImageUrl(),
            product.getRating(),
            product.getDescription(),
            product.getStock(),
            product.getStatus(),
            product.getCategory()
        )).toList();
    }

    public ProductsResponse updaProductsResponse(Long id, UpdateProduct updateProduct){
        Products product = productsRepository.findById(id).get();
        product.setName(updateProduct.name());
        product.setPrice(updateProduct.price());
        product.setImageUrl(updateProduct.imageUrl());
        product.setDescription(updateProduct.description());
        product.setStock(updateProduct.stock());
        product.setStatus(updateProduct.status());
        productsRepository.save(product);
        return new ProductsResponse(
            product.getId(),
            product.getName(),
            product.getPrice(),
            product.getImageUrl(),
            product.getRating(),
            product.getDescription(),
            product.getStock(),
            product.getStatus(),
            product.getCategory()
        );

    }
}
