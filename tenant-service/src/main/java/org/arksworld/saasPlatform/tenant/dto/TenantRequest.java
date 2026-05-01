package org.arksworld.saasPlatform.tenant.dto;

import lombok.Data;

@Data
public class TenantRequest {
    private String tenantName;
    private String tier;
}