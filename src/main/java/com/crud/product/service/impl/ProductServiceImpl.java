package com.crud.product.service.impl;

import com.crud.product.dto.ProductDTO;
import com.crud.product.entity.Product;
import com.crud.product.exception.ResourceNotFoundException;
import com.crud.product.repository.ProductRepository;
import com.crud.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " not found"));
        return new ProductDTO(product);
    }

    @Override
    public ProductDTO create(ProductDTO productDTO) {
        Product product = productRepository.save(productDTO.toEntity());
        return new ProductDTO(product);
    }

    @Override
    public ProductDTO update(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " not found"));

        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());

        product = productRepository.save(product);
        return new ProductDTO(product);
    }

    @Override
    public void delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " not found"));
        productRepository.delete(product);
    }
}
