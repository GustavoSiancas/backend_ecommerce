package com.example.EcommerceBackend.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "wishlist")
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name="is_active")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Products product;

    @PrePersist
    private void prePersist(){
        this.isActive = true;
    }
}
