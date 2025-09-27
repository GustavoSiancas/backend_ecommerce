package com.example.EcommerceBackend.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.EcommerceBackend.entities.OrdersItem;

@Repository
public interface OrdersItemRepository extends JpaRepository<OrdersItem, Long> {
    
}
