package org.arksworld.saasPlatform.order.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    private String id;
    private String userId;

    private double amount;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}