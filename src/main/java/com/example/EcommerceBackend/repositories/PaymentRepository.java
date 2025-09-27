package com.example.EcommerceBackend.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;       
import com.example.EcommerceBackend.entities.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
