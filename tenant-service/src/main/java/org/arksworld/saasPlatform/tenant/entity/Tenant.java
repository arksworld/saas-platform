package org.arksworld.saasPlatform.tenant.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "tenants")
@Data
public class Tenant {

    @Id
    private String tenantId;

    private String tenantName;

    private String status;

    private String tier;

    private String shardId;

    private String schemaName;

    private String dbUrl;

    private LocalDateTime createdAt;
}
