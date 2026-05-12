package org.arksworld.saasPlatform.orderworker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "order_event_audit")
@Data
public class OrderEventAudit {

    @Id
    private UUID id;

    private String orderId;

    private String eventType;

    private Instant processedAt;
}