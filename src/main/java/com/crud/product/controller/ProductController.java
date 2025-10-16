package com.crud.product.controller;

import com.crud.product.dto.ProductDTO;
import com.crud.product.entity.Product;
import com.crud.product.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Products", description = "Products operations")
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {

        this.productService = productService;
    }

    @GetMapping
    @Operation(summary = "Find all products ", description = "Returns all products")
    public List<ProductDTO> findAll(){

       return productService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find product by ID", description = "Returns a product by its ID")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        ProductDTO productDTO = productService.findById(id);
        return ResponseEntity.ok(productDTO);
    }

    @PostMapping
    @Operation(summary = "Create a new product", description = " Returns a product created")
    public ResponseEntity<ProductDTO> create(@RequestBody @Valid ProductDTO productDTO){

        ProductDTO productCreated = productService.create(productDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(productCreated);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a product by ID", description = "Updates the name, price, and quantity of a product")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id , @Valid @RequestBody ProductDTO productDTO) {

        ProductDTO productUpdated = productService.update(id , productDTO);
         return ResponseEntity.ok(productUpdated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete product by ID", description = "Returns a product deleted by its ID")
     public ResponseEntity<void> delete(@PathVariable Long id){

         productService.delete(id);

         return ResponseEntity.noContent().build();
     }
}
