package org.arksworld.saasPlatform.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TenantRoutingInfo {
    private String tenantId;
    private String shardId;
    private String dbUrl;
    private String schema;
}