package org.arksworld.saasPlatform.order.resolver;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderTenantResolver  {


    @Qualifier("controlJdbcTemplate")
    private final JdbcTemplate controlJdbcTemplate;


    public String resolveShard(String tenantId) {

        log.info("Resolving tenant {}", tenantId);
        return controlJdbcTemplate.queryForObject(
                "SELECT shard_id FROM tenants WHERE tenant_id = ?",
                String.class,
                tenantId
        );
    }


    public String resolveSchema(String tenantId) {
        log.info("resolveSchema tenant {}", tenantId);
        return "tenant_" + tenantId.replace("-", "_");
    }
}