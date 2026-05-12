package org.arksworld.saasPlatform.tenant.config;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.arksworld.saasPlatform.tenant.hibernate.SchemaMultiTenantConnectionProvider;
import org.arksworld.saasPlatform.tenant.hibernate.TenantIdentifierResolver;
import org.arksworld.saasPlatform.tenant.resolver.TenantResolver;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class HibernateMultiTenantConfig {

    private final DataSource dataSource;
    private final TenantResolver tenantResolver;

    @Bean
    public MultiTenantConnectionProvider<String>
    multiTenantConnectionProvider() {

        log.info("Initializing MultiTenantConnectionProvider");

        return new SchemaMultiTenantConnectionProvider(
                dataSource,
                tenantResolver
        );
    }

    @Bean
    public CurrentTenantIdentifierResolver<String>
    tenantIdentifierResolver() {

        log.info("Initializing TenantIdentifierResolver");

        return new TenantIdentifierResolver();
    }

    @Bean
    public HibernatePropertiesCustomizer
    hibernatePropertiesCustomizer(
            MultiTenantConnectionProvider<String> provider,
            CurrentTenantIdentifierResolver<String> resolver) {

        return properties -> {

            log.info("Applying Hibernate multi-tenancy properties");

            properties.put(
                    AvailableSettings.MULTI_TENANT_CONNECTION_PROVIDER,
                    provider
            );

            properties.put(
                    AvailableSettings.MULTI_TENANT_IDENTIFIER_RESOLVER,
                    resolver
            );

            /**
             * Hibernate 6 style
             */
            properties.put(
                    "hibernate.multiTenancy",
                    "SCHEMA"
            );
        };
    }
}