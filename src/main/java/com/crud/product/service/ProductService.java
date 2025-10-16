package com.crud.product.service;

import com.crud.product.dto.ProductDTO;
import com.crud.product.entity.Product;
import com.crud.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }

    public List<ProductDTO> findAll(){

        return productRepository.findAll().stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList())
    }

    public ProductDTO create(ProductDTO productDTO) {

        Product product = productRepository.save(productDTO);
        return new ProductDTO(product);

    }

    public ProductDTO update (Long id , ProductDTO productDTO) {

            Product product = productRepository.findById(id).orElseThrow(() -> new Resource)




    }
}
