package org.arksworld.saasPlatform.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    private String id;

    private String name;
    private Double price;
}