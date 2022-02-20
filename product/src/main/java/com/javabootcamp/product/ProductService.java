package com.javabootcamp.product;

import com.javabootcamp.clients.product.ProductCheckResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public ProductCheckResponse checkIfProductExistByProductId(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        Boolean isProductExist;
        if(optionalProduct.isEmpty()) {
            isProductExist = false;
        } else {
            isProductExist = true;
        }

        return new ProductCheckResponse(isProductExist);
    }
}
