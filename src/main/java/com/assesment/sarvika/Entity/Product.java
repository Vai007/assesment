package com.assesment.sarvika.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "name")
    @NotBlank(message = "Product name cannot be empty or null")
    private String name;

    @Column(name = "description")
    @Length(max = 10)
    private String description;

    @Column(name = "price")
    @NotNull(message = "price value cannot be null")
    private Double price;

    @Column(name = "created", updatable = false)
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "updated")
    @UpdateTimestamp
    private Instant updatedAt;
}
