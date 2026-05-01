package org.arksworld.saasPlatform.product.datasource;

import lombok.RequiredArgsConstructor;
import org.arksworld.saasPlatform.common.dto.TenantContext;
import org.arksworld.saasPlatform.common.dto.TenantRoutingInfo;
import org.arksworld.saasPlatform.product.tenant.TenantRoutingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;




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

        System.out.println("Inside get connection..");
        String tenantId = TenantContext.get();
        System.out.println("Tenant id:" + tenantId);
        if (tenantId == null) {
            return defaultDataSource.getConnection();
        }
        TenantRoutingInfo info = routingService.resolve(tenantId);
        System.out.println("Tenant info:" + info);
        DataSource ds = registry.getDataSource(info.getShardId(), info.getDbUrl());
        Connection conn = ds.getConnection();

        conn.createStatement()
                .execute("SET search_path TO " + info.getSchema());

        return conn;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return getConnection();
    }
}