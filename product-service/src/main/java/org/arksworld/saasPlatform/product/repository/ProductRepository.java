package org.arksworld.saasPlatform.product.repository;

import org.arksworld.saasPlatform.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}