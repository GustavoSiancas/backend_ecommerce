package com.example.EcommerceBackend.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.EcommerceBackend.entities.Products;
import com.example.EcommerceBackend.entities.enums.Status;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {
    List<Products> findByStatus(Status status);
    List<Products> findByUser_Id(Long userId);
}
