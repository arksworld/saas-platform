package org.arksworld.saasPlatform.order.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.arksworld.saasPlatform.common.events.BaseEvent;
import org.arksworld.saasPlatform.tenant.kafka.TenantAwareKafkaConsumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventConsumer extends TenantAwareKafkaConsumer {

    private final ObjectMapper objectMapper;
    private final OrderEventHandler handler;

    @KafkaListener(
            topics = {"payment-events", "inventory-events"},
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(BaseEvent event) {

        switch (event.getEventType()) {

            case "PAYMENT_COMPLETED":
                handler.handlePaymentSuccess(event);
                break;

            case "PAYMENT_FAILED":
                handler.handlePaymentFailure(event);
                break;

            case "INVENTORY_RESERVED":
                handler.handleInventorySuccess(event);
                break;

            case "INVENTORY_FAILED":
                handler.handleInventoryFailure(event);
                break;
        }
    }
}