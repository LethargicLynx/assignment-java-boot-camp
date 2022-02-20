package com.javabootcamp.customer.cart.product;

import org.springframework.stereotype.Service;

@Service
public record CartProductService(CartProductRepository cartProductRepository) {
}
