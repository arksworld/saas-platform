package org.arksworld.saasPlatform.tenant.service;

import lombok.RequiredArgsConstructor;
import org.arksworld.saasPlatform.tenant.dto.TenantRequest;
import org.arksworld.saasPlatform.tenant.dto.TenantResponse;
import org.arksworld.saasPlatform.tenant.entity.Shard;
import org.arksworld.saasPlatform.tenant.entity.Tenant;
import org.arksworld.saasPlatform.tenant.kafka.events.TenantCreatedEvent;
import org.arksworld.saasPlatform.tenant.kafka.service.TenantEventProducer;
import org.arksworld.saasPlatform.tenant.repository.TenantRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TenantService {

    private final TenantRepository tenantRepository;
    private final ShardAllocator shardAllocator;
    private final SchemaProvisioningService schemaService;
    private final MigrationService migrationService;
    private final TenantEventProducer tenantEventProducer;

    public TenantResponse createTenant(TenantRequest request) {

        String tenantId = UUID.randomUUID().toString();
        String schema = "tenant_" + tenantId.replace("-", "");

        // Step 1: Allocate shard
        Shard shard = shardAllocator.allocateShard();

        // Step 2: Create schema
        schemaService.createSchema(shard, schema);

        // Step 3: Run migrations
        //migrationService.migrateCore(shard, schema);
        migrationService.migrateProduct(shard, schema);
       // migrationService.migrateALl(shard, schema);

        // Step 4: Save metadata
        Tenant tenant = new Tenant();
        tenant.setTenantId(tenantId);
        tenant.setTenantName(request.getTenantName());
        tenant.setTier(request.getTier());
        tenant.setStatus("ACTIVE");
        tenant.setShardId(shard.getShardId());
        tenant.setSchemaName(schema);
        tenant.setDbUrl(shard.getDbUrl());
        tenant.setCreatedAt(LocalDateTime.now());

        tenantRepository.save(tenant);

        //Publish kafka
        tenantEventProducer.publishTenantCreated(new TenantCreatedEvent(tenantId, "admin", "pass"));

        return new TenantResponse(tenantId, shard.getShardId(), schema);
    }
}