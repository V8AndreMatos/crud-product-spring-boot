package com.crud.product.service;

import com.crud.product.dto.ProductDTO;
import com.crud.product.entity.Product;
import com.crud.product.repository.ProductRepository;
import com.crud.product.exception.ResourceNotFoundException;
import java.util.List;
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
                .collect(Collectors.toList());
    }

    public ProductDTO findById (Long id) {

        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id : " +id+ " Not Found"));

        return new ProductDTO(product);

    }

    public ProductDTO create(ProductDTO productDTO) {

        Product product = productRepository.save(productDTO.toEntity());

        return new ProductDTO(product);

    }

    public ProductDTO update (Long id , ProductDTO productDTO) {

            Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id" +id+ " Not Found "));

            product.setName(productDTO.getName());
            product.setPrice(productDTO.getPrice());
            product.setQuantity(productDTO.getQuantity());

            product = productRepository.save(product);
            return new ProductDTO(product);
    }

    public void delete(Long id){

           Product product = productRepository.findById(id)
                   .orElseThrow(() -> new ResourceNotFoundException("Product with id" +id+ " Not Found "));
                    productRepository.deleteById(id);

    }
}
