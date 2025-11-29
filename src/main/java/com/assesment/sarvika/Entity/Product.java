package com.assesment.sarvika.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.Instant;
import java.util.Objects;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public @NotBlank(message = "Product name cannot be empty or null") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Product name cannot be empty or null") String name) {
        this.name = name;
    }

    public @Length(max = 10) String getDescription() {
        return description;
    }

    public void setDescription(@Length(max = 10) String description) {
        this.description = description;
    }

    public @NotNull(message = "price value cannot be null") Double getPrice() {
        return price;
    }

    public void setPrice(@NotNull(message = "price value cannot be null") Double price) {
        this.price = price;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(price, product.price) && Objects.equals(createdAt, product.createdAt) && Objects.equals(updatedAt, product.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
