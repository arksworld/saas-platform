package org.arksworld.saasPlatform.tenant.hibernate;


import lombok.extern.slf4j.Slf4j;
import org.arksworld.saasPlatform.common.dto.TenantContext;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TenantIdentifierResolver
        implements CurrentTenantIdentifierResolver<String> {

    @Override
    public String resolveCurrentTenantIdentifier() {

        String tenantId = TenantContext.get();
        log.info("Tenant Id:{}", tenantId);

        return tenantId != null
                ? tenantId
                : "default";
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}