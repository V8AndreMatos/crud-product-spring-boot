package com.crud.product.repository;

import com.crud.product.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Testcontainers
class ProductRepositoryTest {

    @Autowired
    private ProductRepository repository;

    @Test
    void shouldSaveAndRetrieveProduct() {
        Product product = new Product(null, "Mouse Gamer", 199.90, 3);
        Product saved = repository.save(product);

        assertNotNull(saved.getId());
        assertEquals("Mouse Gamer", saved.getName());

        Product found = repository.findById(saved.getId()).orElseThrow();
        assertEquals(199.90, found.getPrice());
    }
}
