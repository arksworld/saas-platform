package org.arksworld.saasPlatform.tenant.hibernate;

import lombok.RequiredArgsConstructor;
import org.arksworld.saasPlatform.tenant.resolver.TenantResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class SchemaMultiTenantConnectionProvider
        implements MultiTenantConnectionProvider<String> {

    private final DataSource dataSource;
    private final TenantResolver tenantResolver;

    /**
     * Used by Hibernate during startup
     */
    @Override
    public Connection getAnyConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * Release startup connection
     */
    @Override
    public void releaseAnyConnection(Connection connection)
            throws SQLException {

        connection.close();
    }

    /**
     * Main tenant-aware connection method
     */
    @Override
    public Connection getConnection(String tenantIdentifier)
            throws SQLException {

        Connection connection = getAnyConnection();

        String schema =
                tenantResolver.resolveSchema(tenantIdentifier);

        log.info("Tenant: {}", tenantIdentifier);

        try (Statement stmt = connection.createStatement()) {

            stmt.execute("SET search_path TO " + schema);

            log.debug("Switched to schema: {}", schema);
        }

        return connection;
    }

    /**
     * Reset schema before returning connection
     */
    @Override
    public void releaseConnection(
            String tenantIdentifier,
            Connection connection)
            throws SQLException {

        try (Statement stmt = connection.createStatement()) {

            stmt.execute("SET search_path TO public");

        } finally {
            connection.close();
        }
    }

    /**
     * Aggressive release usually false for Spring apps
     */
    @Override
    public boolean supportsAggressiveRelease() {
        return false;
    }

    /**
     * Hibernate unwrap support
     */
    @Override
    public boolean isUnwrappableAs(Class<?> unwrapType) {

        return
                MultiTenantConnectionProvider.class.equals(unwrapType)
                        || SchemaMultiTenantConnectionProvider.class.isAssignableFrom(unwrapType);
    }

    /**
     * Hibernate unwrap support
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T unwrap(Class<T> unwrapType) {

        if (isUnwrappableAs(unwrapType)) {
            return (T) this;
        }

        return null;
    }
}