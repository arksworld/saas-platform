package org.arksworld.saasPlatform.tenant.kafka;

import lombok.extern.slf4j.Slf4j;
import org.arksworld.saasPlatform.common.dto.TenantContext;
import org.slf4j.MDC;

@Slf4j
public class KafkaTenantContextExecutor {

    public static void execute(
            String tenantId,
            Runnable runnable) {

        try {

            TenantContext.set(tenantId);

            MDC.put("tenantId", tenantId);
            log.debug("TenantContext set: {}", tenantId);

            runnable.run();

        } finally {

            TenantContext.clear();

            log.debug("TenantContext cleared");
        }
    }
}