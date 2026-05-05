package org.arksworld.saasPlatform.tenant.kafka.service;

import lombok.RequiredArgsConstructor;
import org.arksworld.saasPlatform.common.events.BaseEvent;
import org.arksworld.saasPlatform.tenant.kafka.events.TenantCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TenantEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishTenantCreated(TenantCreatedEvent event) {

        BaseEvent<TenantCreatedEvent> tenantCreatedEventBaseEvent = new BaseEvent<>();
        tenantCreatedEventBaseEvent.setEventType("TENANT_CREATED");
        tenantCreatedEventBaseEvent.setEventId(UUID.randomUUID().toString());
        tenantCreatedEventBaseEvent.setTenantId(event.getTenantId());
        tenantCreatedEventBaseEvent.setTimestamp(System.currentTimeMillis());
        tenantCreatedEventBaseEvent.setPayload(event);
        kafkaTemplate.send("tenant-events", tenantCreatedEventBaseEvent);
        System.out.println("Event published:" + event);
    }
}