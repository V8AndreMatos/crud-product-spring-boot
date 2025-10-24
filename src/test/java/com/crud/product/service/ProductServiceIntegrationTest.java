package com.crud.product.service;

import com.crud.product.dto.ProductDTO;
import com.crud.product.exception.ResourceNotFoundException;
import com.crud.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
@ActiveProfiles("test")
class ProductServiceIntegrationTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldCreateAndFindProduct() {
        ProductDTO dto = new ProductDTO(null, "Mouse", 99.90, 5);
        ProductDTO saved = productService.create(dto);

        ProductDTO found = productService.findById(saved.getId());

        assertEquals("Mouse", found.getName());
        assertEquals(99.90, found.getPrice());
        assertEquals(5, found.getQuantity());
    }

    @Test
    void shouldUpdateProduct() {
        ProductDTO dto = new ProductDTO(null, "Teclado", 150.00, 3);
        ProductDTO saved = productService.create(dto);

        ProductDTO updated = new ProductDTO(null, "Teclado Mecânico", 250.00, 3);
        ProductDTO result = productService.update(saved.getId(), updated);

        assertEquals("Teclado Mecânico", result.getName());
        assertEquals(250.00, result.getPrice());
    }

    @Test
    void shouldDeleteProduct() {
        ProductDTO dto = new ProductDTO(null, "Monitor", 899.00, 2);
        ProductDTO saved = productService.create(dto);

        productService.delete(saved.getId());

        assertThrows(ResourceNotFoundException.class, () -> productService.findById(saved.getId()));
    }

    @Test
    void shouldFindAllProducts() {
        productRepository.deleteAll(); // limpa o banco para garantir consistência

        productService.create(new ProductDTO(null, "Produto 1", 10.0, 1));
        productService.create(new ProductDTO(null, "Produto 2", 20.0, 2));

        List<ProductDTO> all = productService.findAll();

        assertEquals(2, all.size());
    }
}
