package org.arksworld.saasPlatform.tenant.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.arksworld.saasPlatform.tenant.dto.TenantRoutingInfo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class TenantRoutingService {

    private final @Qualifier("controlJdbcTemplate")  JdbcTemplate jdbcTemplate;
    Map<String, TenantRoutingInfo> resolvedDataSources = new HashMap<>();



    public TenantRoutingInfo resolve(String tenantId) {
        log.info("Resolve {}", tenantId);
        // 1. Check cache
        return resolvedDataSources.get(tenantId) == null?  fetchAndCache(tenantId) : resolvedDataSources.get(tenantId);
    }

    private TenantRoutingInfo fetchAndCache(String tenantId) {
        log.info("fetchAndCache {}", tenantId);
        String sql = """
            SELECT tenant_id, shard_id, db_url, schema_name
            FROM tenants
            WHERE tenant_id = ?
        """;

        return jdbcTemplate.queryForObject(sql,
                (rs, rowNum) -> {

                    TenantRoutingInfo info = new TenantRoutingInfo(
                            rs.getString("tenant_id"),
                            rs.getString("shard_id"),
                            rs.getString("db_url"),
                            rs.getString("schema_name")
                    );

                    resolvedDataSources.put(tenantId, info);
                    return info;
                },
                tenantId
        );
    }
}