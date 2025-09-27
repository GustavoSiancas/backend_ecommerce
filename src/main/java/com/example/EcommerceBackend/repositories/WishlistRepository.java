package com.example.EcommerceBackend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.EcommerceBackend.entities.Wishlist;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long>{
    List<Wishlist> findByUser_IdAndIsActive(Long userId, Boolean isActive);
    Optional<Wishlist> findByUser_IdAndProduct_Id(Long userId, Long productId);

}
