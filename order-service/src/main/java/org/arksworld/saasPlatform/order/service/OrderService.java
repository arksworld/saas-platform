package org.arksworld.saasPlatform.order.service;

import org.arksworld.saasPlatform.order.dto.CreateOrderRequest;
import org.arksworld.saasPlatform.order.entity.Order;
import org.arksworld.saasPlatform.order.entity.OrderStatus;
import org.arksworld.saasPlatform.order.events.OrderEventPublisher;
import org.arksworld.saasPlatform.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderEventPublisher publisher;

    public OrderService(OrderRepository orderRepository, OrderEventPublisher publisher) {
        this.orderRepository = orderRepository;
        this.publisher = publisher;
    }

    public Order createOrder(CreateOrderRequest request) {

        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setUserId(request.getUserId());
        order.setAmount(request.getAmount());
        order.setStatus(OrderStatus.CREATED);

        orderRepository.save(order);

        publisher.publishOrderCreated(order);

        return order;
    }

    public void updateStatus(String orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.setStatus(status);
        orderRepository.save(order);
    }
}