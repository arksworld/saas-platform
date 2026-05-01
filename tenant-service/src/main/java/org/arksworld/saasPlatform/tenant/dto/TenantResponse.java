package org.arksworld.saasPlatform.tenant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TenantResponse {
    private String tenantId;
    private String shardId;
    private String schemaName;
}
