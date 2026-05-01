package org.arksworld.saasPlatform.product.controller;

import lombok.RequiredArgsConstructor;
import org.arksworld.saasPlatform.product.entity.Product;
import org.arksworld.saasPlatform.product.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping
    public Product create(@RequestBody Product product) {
        return service.create(product);
    }
}