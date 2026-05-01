package org.arksworld.saasPlatform.product.tenant;

import lombok.RequiredArgsConstructor;
import org.arksworld.saasPlatform.common.dto.TenantRoutingInfo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TenantRoutingService {

    private final @Qualifier("controlJdbcTemplate")  JdbcTemplate jdbcTemplate;
    private final TenantCacheService cacheService;


    public TenantRoutingInfo resolve(String tenantId) {

        // 1. Check cache
        return cacheService.get(tenantId)
                .orElseGet(() -> fetchAndCache(tenantId));
    }

    private TenantRoutingInfo fetchAndCache(String tenantId) {

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

                    cacheService.put(tenantId, info);
                    return info;
                },
                tenantId
        );
    }
}