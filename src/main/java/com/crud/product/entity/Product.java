package com.crud.product.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_products")
@Schema(description = "Entity that represents a product in the database")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID of the product ", example = "1")
    private Long id;

    @Schema(description = "User full name", example = "André Matos")
    @NotBlank(message = "Name is required")
    private String name;

    @Schema(description = "Price of the product", example = "77.90")
    @NotNull(message = "Price is required")
    private Double price;

    @Schema(description = "Quantity of the product", example = "10")
    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity must be zero or more")
    private Integer quantity;

    public Product() {
    }

    public Product(Long id, String name, Double price, Integer quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
