package com.example.EcommerceBackend.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.EcommerceBackend.entities.enums.OrderType;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Additional fields and relationships can be added here
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name="created_at", columnDefinition = "DATE")
    private LocalDateTime createdAt;

    @Column(name="udpated_at", columnDefinition = "DATE")
    private LocalDateTime updatedAt;

    @Column(name="order_type", nullable = false)
    private OrderType orderType;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrdersItem> orderItems;

    private BigDecimal total;

    @PrePersist
    private void prePersist(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.total = new BigDecimal(0);
        this.orderType = OrderType.CART;
    }

    @PreUpdate
    private void preUpdate(){
        this.updatedAt = LocalDateTime.now();
    }

    
}
