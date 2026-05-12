package org.arksworld.saasPlatform.orderworker.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.arksworld.saasPlatform.common.events.BaseEvent;
import org.arksworld.saasPlatform.orderworker.entity.OrderEventAudit;
import org.arksworld.saasPlatform.orderworker.repository.OrderEventAuditRepository;
import org.arksworld.saasPlatform.tenant.kafka.TenantAwareKafkaConsumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderWorkerConsumer
        extends TenantAwareKafkaConsumer {

    private final ObjectMapper objectMapper;
    private final OrderEventAuditRepository repository;

    @KafkaListener(
            topics = "order-events",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(BaseEvent<?> event) {

        execute(event, () -> {

            if ("ORDER_CREATED".equals(event.getEventType())) {

                Map payload = (Map) event.getPayload();

                String orderId =
                        (String) payload.get("orderId");

                OrderEventAudit audit =
                        new OrderEventAudit();

                audit.setId(UUID.randomUUID());
                audit.setOrderId(orderId);
                audit.setEventType(event.getEventType());
                audit.setProcessedAt(Instant.now());

                repository.save(audit);
            }
        });
    }
}