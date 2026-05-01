package org.arksworld.saasPlatform.tenant.kafka.service;

import lombok.RequiredArgsConstructor;
import org.arksworld.saasPlatform.tenant.kafka.events.TenantCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TenantEventProducer {

    private final KafkaTemplate<String, TenantCreatedEvent> kafkaTemplate;

    public void publishTenantCreated(TenantCreatedEvent event) {
        kafkaTemplate.send("tenant-created", event);
        System.out.println("Event published:" + event);
    }
}