package com.crud.product.dto;

import com.crud.product.entity.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "Object representing a product")
public class ProductDTO {

        @Schema(description = "ID of the product", example = "1")
        private Long id;

        @NotBlank(message = "{product.name.required}")
        @Schema(description = "Name of the product", example = "Notebook Dell Inspiron")
        private String name;

        @DecimalMin(value = "1.00", message = "{product.price.min}")
        @DecimalMax(value = "10000.00", message = "{product.price.max}")
        @Schema(description = "Price of the product", example = "77.90")
        private Double price;

        @Min(value = 0, message = "{product.quantity.min}")
        @Max(value = 1000, message = "{product.quantity.max}")
        @Schema(description = "Quantity of the product", example = "10")
        private Integer quantity;

    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, Double price, Integer quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public ProductDTO(Product entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.price = entity.getPrice();
        this.quantity = entity.getQuantity();
    }

    public Product toEntity() {
        Product product = new Product();
        product.setId(this.id);
        product.setName(this.name);
        product.setPrice(this.price);
        product.setQuantity(this.quantity);
        return product;
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
