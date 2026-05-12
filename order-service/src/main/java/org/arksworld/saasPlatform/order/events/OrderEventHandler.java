package org.arksworld.saasPlatform.order.events;

import lombok.RequiredArgsConstructor;
import org.arksworld.saasPlatform.common.events.BaseEvent;
import org.arksworld.saasPlatform.order.entity.OrderStatus;
import org.arksworld.saasPlatform.order.service.OrderService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class OrderEventHandler {

    private final OrderService orderService;

    public void handlePaymentSuccess(BaseEvent event) {
        String orderId = extractOrderId(event);
        orderService.updateStatus(orderId, OrderStatus.PAYMENT_COMPLETED);
    }

    public void handlePaymentFailure(BaseEvent event) {
        String orderId = extractOrderId(event);
        orderService.updateStatus(orderId, OrderStatus.CANCELLED);
    }

    public void handleInventorySuccess(BaseEvent event) {
        String orderId = extractOrderId(event);
        orderService.updateStatus(orderId, OrderStatus.COMPLETED);
    }

    public void handleInventoryFailure(BaseEvent event) {
        String orderId = extractOrderId(event);
        orderService.updateStatus(orderId, OrderStatus.CANCELLED);
    }

    private String extractOrderId(BaseEvent event) {
        Map payload = (Map) event.getPayload();
        return (String) payload.get("orderId");
    }
}