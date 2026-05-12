package org.arksworld.saasPlatform.order.entity;

public enum OrderStatus {
    CREATED,
    PAYMENT_PENDING,
    PAYMENT_FAILED,
    PAYMENT_COMPLETED,
    INVENTORY_RESERVED,
    COMPLETED,
    CANCELLED
}
