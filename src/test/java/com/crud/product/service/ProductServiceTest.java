package com.crud.product.service;

import com.crud.product.dto.ProductDTO;
import com.crud.product.entity.Product;
import com.crud.product.repository.ProductRepository;
import com.crud.product.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    private Product product;
    private ProductDTO productDTO;

    @BeforeEach
    void setUp() {
        product = new Product(1L, "Notebook", 1999.99, 10);
        productDTO = new ProductDTO(1L, "Notebook", 1999.99, 10);
    }

    @Test
    void shouldFindAllProducts() {
        when(productRepository.findAll()).thenReturn(List.of(product));

        List<ProductDTO> result = productService.findAll();

        assertEquals(1, result.size());
        assertEquals("Notebook", result.get(0).getName());
    }

    @Test
    void shouldFindProductById() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        ProductDTO result = productService.findById(1L);

        assertEquals("Notebook", result.getName());
    }

    @Test
    void shouldCreateProduct() {
        when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductDTO result = productService.create(productDTO);

        assertEquals("Notebook", result.getName());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void shouldUpdateProduct() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductDTO result = productService.update(1L, productDTO);

        assertEquals(1999.99, result.getPrice());
    }

    @Test
    void shouldDeleteProduct() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        doNothing().when(productRepository).delete(product);

        productService.delete(1L);

        verify(productRepository, times(1)).delete(product);
    }
}
