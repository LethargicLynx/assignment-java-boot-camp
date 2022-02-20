package com.javabootcamp.clients.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("product")
public interface ProductClient {

    @GetMapping(path = "api/v1/products/product-check/{productId}")
    ProductCheckResponse isProductExists( @PathVariable("productId") Integer productId);
}
