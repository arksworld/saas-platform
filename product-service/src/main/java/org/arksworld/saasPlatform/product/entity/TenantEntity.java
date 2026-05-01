package org.arksworld.saasPlatform.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tenants")
@Data
public class TenantEntity {
    @Id
    private String tenantId;

    private String shardId;
    private String dbUrl;
    private String schemaName;
}