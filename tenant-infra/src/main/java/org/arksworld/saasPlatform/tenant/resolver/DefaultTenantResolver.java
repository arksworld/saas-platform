package org.arksworld.saasPlatform.tenant.resolver;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultTenantResolver implements TenantResolver {

    @Override
    public String resolveShard(String tenantId) {
        log.info("resolveShard tenantId: {}", tenantId);
        // fallback
        return "shard1";
    }

    @Override
    public String resolveSchema(String tenantId) {
        log.info("resolveSchema tenantId: {}", tenantId);
        return "tenant_" + tenantId.replace("-", "_");
    }
}