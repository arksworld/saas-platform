package org.arksworld.saasPlatform.auth.kafka.service;



import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.arksworld.saasPlatform.auth.kafka.entity.ProcessedEvent;
import org.arksworld.saasPlatform.auth.kafka.handler.TenantEventHandler;
import org.arksworld.saasPlatform.auth.kafka.repository.ProcessedEventRepository;
import org.arksworld.saasPlatform.common.events.BaseEvent;
import org.arksworld.saasPlatform.common.events.tenant.TenantCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TenantEventConsumer {

    private final ObjectMapper objectMapper;
    private final TenantEventHandler handler;
    private final ProcessedEventRepository processedEventRepository;

    @KafkaListener(
            topics = "tenant-events",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(BaseEvent event) {

        if(processedEventRepository.existsById(event.getEventId())) {
            return;
        }

        switch (event.getEventType()) {

            case "TENANT_CREATED":
                TenantCreatedEvent payload =
                        objectMapper.convertValue(event.getPayload(), TenantCreatedEvent.class);

                handler.handleTenantCreated(event, payload);
                break;

            default:
                System.out.println("Unknown event: " + event.getEventType());
        }
        ProcessedEvent processedEvent = new ProcessedEvent(event.getEventId());
        processedEventRepository.save(processedEvent);
    }
}