package org.arksworld.saasPlatform.tenant.kafka;


import org.arksworld.saasPlatform.common.events.BaseEvent;

public abstract class TenantAwareKafkaConsumer {

    protected void execute(BaseEvent<?> event, Runnable runnable) {

        String tenantId = event.getTenantId();

        if (tenantId == null) {
            throw new IllegalStateException(
                    "TenantId missing in event"
            );
        }

        KafkaTenantContextExecutor.execute(
                tenantId,
                runnable
        );
    }
}