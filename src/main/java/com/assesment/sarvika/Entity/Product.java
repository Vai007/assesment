package com.assesment.sarvika.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    @Size(min = 3, max = 15, message = "Name should in range of 3 to 15")
    private String name;

    @Column(name = "description")
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @Column(name = "price")
    @Digits(integer = 5, fraction = 2, message = "Price can have max 2 decimal places")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be a positive number")
    @NotNull(message = "price value cannot be null")
    private BigDecimal price;

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

    public @Size(min = 3, max = 15, message = "Name should in range of 3 to 15") String getName() {
        return name;
    }

    public void setName(@Size(min = 3, max = 15, message = "Name should in range of 3 to 15") String name) {
        this.name = name;
    }

    public @Size(max = 500, message = "Description cannot exceed 500 characters") String getDescription() {
        return description;
    }

    public void setDescription(@Size(max = 500, message = "Description cannot exceed 500 characters") String description) {
        this.description = description;
    }

    public @Digits(integer = 5, fraction = 2, message = "Price can have max 2 decimal places") @DecimalMin(value = "0.0", inclusive = false, message = "Price must be a positive number") @NotNull(message = "price value cannot be null") BigDecimal getPrice() {
        return price;
    }

    public void setPrice(@Digits(integer = 5, fraction = 2, message = "Price can have max 2 decimal places") @DecimalMin(value = "0.0", inclusive = false, message = "Price must be a positive number") @NotNull(message = "price value cannot be null") BigDecimal price) {
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
