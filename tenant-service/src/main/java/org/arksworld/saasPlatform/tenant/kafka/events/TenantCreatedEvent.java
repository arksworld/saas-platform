package org.arksworld.saasPlatform.tenant.kafka.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TenantCreatedEvent {
    private String tenantId;
    private String adminUsername;
    private String adminPassword;
}