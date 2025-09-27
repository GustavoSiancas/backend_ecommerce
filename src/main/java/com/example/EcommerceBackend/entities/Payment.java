package com.example.EcommerceBackend.entities;

import java.math.BigDecimal;

import com.example.EcommerceBackend.entities.enums.PaymentStatus;

import java.time.LocalDateTime;
import jakarta.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus paymentStatus;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(columnDefinition = "DATE", name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Orders order;
    
    @PrePersist
    private void prePersist(){
        this.createdAt = LocalDateTime.now();
    }
}
