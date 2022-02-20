package com.javabootcamp.product;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public record ProductController(ProductService productService) {

    @GetMapping
    public List<Product> getAllProduct() {
        return productService.getAllProduct();
    }

    @GetMapping
    public List<Product> getProductByNameContaining(@RequestParam("search") String search) {
        return productService.getProductsByNameContaining(search);
    }

    @GetMapping(path = "/product/{id}")
    public Product getProductById(@PathVariable("id") Integer id) {
        return productService.getProductById(id);
    }

}
