package com.example.EcommerceBackend.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.EcommerceBackend.entities.Orders;
import com.example.EcommerceBackend.entities.enums.OrderType;

import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    Optional<Orders> findByUser_IdAndOrderType(Long userId, OrderType orderType);
}
