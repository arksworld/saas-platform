package org.arksworld.saasPlatform.tenant.routing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.arksworld.saasPlatform.common.dto.TenantContext;
import org.arksworld.saasPlatform.tenant.resolver.TenantResolver;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

@Slf4j
@RequiredArgsConstructor
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    private final TenantResolver tenantResolver;

    @Override
    protected Object determineCurrentLookupKey() {

        String tenantId = TenantContext.get();

        log.info("Got tenant:{}", tenantId);

        if (tenantId == null) return "default";

        return tenantResolver.resolveShard(tenantId);
    }
}