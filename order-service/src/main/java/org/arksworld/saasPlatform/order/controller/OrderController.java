package org.arksworld.saasPlatform.order.controller;

import lombok.RequiredArgsConstructor;
import org.arksworld.saasPlatform.order.dto.CreateOrderRequest;
import org.arksworld.saasPlatform.order.entity.Order;
import org.arksworld.saasPlatform.order.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Order create(@RequestBody CreateOrderRequest request) {
        return orderService.createOrder(request);
    }
}