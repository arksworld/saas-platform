package org.arksworld.saasPlatform.tenant.datasource;

import lombok.extern.slf4j.Slf4j;
import org.arksworld.saasPlatform.tenant.dto.TenantContext;
import org.arksworld.saasPlatform.tenant.dto.TenantRoutingInfo;
import org.arksworld.saasPlatform.tenant.service.TenantRoutingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.AbstractDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
public class DynamicDataSourceRouter extends AbstractDataSource {

    private final TenantRoutingService routingService;
    private final DataSourceRegistry registry;

    public DynamicDataSourceRouter( final TenantRoutingService routingService,  final DataSourceRegistry dataSourceRegistry) {
        this.routingService = routingService;
        this.registry = dataSourceRegistry;
    }

    @Autowired
    private DataSource defaultDataSource;

    @Override
    public Connection getConnection() throws SQLException {

        log.info("Inside get connection..");
        String tenantId = TenantContext.getCurrentTenant();
        log.info("Tenant id: {}", tenantId);
        if (tenantId == null) {
            return defaultDataSource.getConnection();
        }
        TenantRoutingInfo info = routingService.resolve(tenantId);
        log.info("Tenant info: {}", info);
        DataSource ds = registry.getDataSource(info.getShardId(), info.getDbUrl());
        Connection conn = ds.getConnection();

        conn.createStatement()
                .execute("SET search_path TO " + info.getSchema());

        return conn;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        log.info("getConnection: username:{}, password:{}", username, password );
        return getConnection();
    }
}