package org.arksworld.saasPlatform.product.service;

import lombok.RequiredArgsConstructor;
import org.arksworld.saasPlatform.product.entity.Product;
import org.arksworld.saasPlatform.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product create(Product product) {
        product.setId(UUID.randomUUID().toString());
        return productRepository.save(product);
    }
}