package org.arksworld.saasPlatform.tenant.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.arksworld.saasPlatform.tenant.properties.TenantProperties;
import org.arksworld.saasPlatform.tenant.resolver.DefaultTenantResolver;
import org.arksworld.saasPlatform.tenant.resolver.TenantResolver;
import org.arksworld.saasPlatform.tenant.routing.DynamicRoutingDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(TenantProperties.class)
public class DataSourceAutoConfiguration {

    private final TenantProperties tenantProperties;

    /**
     * Default resolver
     * Can be overridden by services
     */
    @Bean
    @ConditionalOnMissingBean
    public TenantResolver tenantResolver() {
        log.info("Loading default tenant resolver");
        return new DefaultTenantResolver();
    }

    /**
     * Main routing datasource
     */
    @Bean
    @Primary
    public DataSource dataSource(TenantResolver tenantResolver) {

        log.info("dataSource:: Resolving tenant  ");
        DynamicRoutingDataSource routingDataSource =
                new DynamicRoutingDataSource(tenantResolver);

        Map<Object, Object> targetDataSources =
                loadShardDataSources();

        routingDataSource.setTargetDataSources(targetDataSources);

        routingDataSource.setDefaultTargetDataSource(
                defaultDataSource(targetDataSources)
        );

        routingDataSource.afterPropertiesSet();

        return routingDataSource;
    }

    /**
     * Load all shard datasources
     */
    private Map<Object, Object> loadShardDataSources() {
        log.info("loadShardDataSources::");
        Map<Object, Object> dataSources = new HashMap<>();

        tenantProperties.getShards()
                .forEach((shardId, props) -> {

                    HikariDataSource ds =
                            new HikariDataSource();

                    ds.setJdbcUrl(props.getUrl());
                    ds.setUsername(props.getUsername());
                    ds.setPassword(props.getPassword());

                    ds.setMaximumPoolSize(10);
                    ds.setMinimumIdle(2);

                    dataSources.put(shardId, ds);
                });

        return dataSources;
    }

    /**
     * Pick first datasource as default
     */
    private Object defaultDataSource(
            Map<Object, Object> dataSources) {

        log.info("defaultDataSource");
        return dataSources.values()
                .stream()
                .findFirst()
                .orElseThrow(() ->
                        new IllegalStateException(
                                "No shard datasource configured"
                        ));
    }
}