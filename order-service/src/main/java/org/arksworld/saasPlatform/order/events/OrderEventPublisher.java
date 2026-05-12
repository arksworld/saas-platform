package org.arksworld.saasPlatform.order.events;

import lombok.RequiredArgsConstructor;
import org.arksworld.saasPlatform.common.dto.TenantContext;
import org.arksworld.saasPlatform.common.events.BaseEvent;
import org.arksworld.saasPlatform.common.events.order.OrderCreatedEvent;
import org.arksworld.saasPlatform.order.entity.Order;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishOrderCreated(Order order) {

        String tenantId = TenantContext.get();
        OrderCreatedEvent payload = OrderCreatedEvent.builder()
                .orderId(order.getId())
                .tenantId(tenantId)
                .userId(order.getUserId())
                .amount(order.getAmount())
                .build();

        BaseEvent<OrderCreatedEvent> event = BaseEvent.<OrderCreatedEvent>builder()
                .eventId(UUID.randomUUID().toString())
                .eventType("ORDER_CREATED")
                .tenantId(tenantId)
                .timestamp(System.currentTimeMillis())
                .payload(payload)
                .build();

        kafkaTemplate.send("order-events", order.getId(), event);
    }
}