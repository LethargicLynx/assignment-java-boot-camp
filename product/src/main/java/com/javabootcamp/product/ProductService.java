package com.javabootcamp.product;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record ProductService(ProductRepository productRepository) {

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public Product getProductById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow();
    }

    public List<Product> getProductsByNameContaining(String nameSearch) {
        return productRepository.findByNameContaining(nameSearch);
    }
}
