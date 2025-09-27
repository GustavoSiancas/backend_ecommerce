package com.example.EcommerceBackend.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.EcommerceBackend.entities.enums.Category;
import com.example.EcommerceBackend.entities.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private Integer stock;

    private Double rating;

    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Enumerated
    @Column(nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Wishlist> wishlist;

    @PrePersist
    private void PrePersist(){
        this.createdAt = LocalDateTime.now();
        this.rating=0.0;
        this.status=Status.ACTIVE;
    }
}
