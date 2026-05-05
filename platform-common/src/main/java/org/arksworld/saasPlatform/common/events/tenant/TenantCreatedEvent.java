package org.arksworld.saasPlatform.common.events.tenant;

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