package org.arksworld.saasPlatform.tenant.config;

import lombok.extern.slf4j.Slf4j;
import org.arksworld.saasPlatform.tenant.datasource.DataSourceRegistry;
import org.arksworld.saasPlatform.tenant.datasource.DynamicDataSourceRouter;
import org.arksworld.saasPlatform.tenant.service.TenantRoutingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class DataSourceConfig {

    @Bean
    @Primary
    public DataSource dataSource(
            TenantRoutingService routingService,
            DataSourceRegistry registry) {

        log.info("Using DynamicDataSourceRouter...............");

        return new DynamicDataSourceRouter(routingService, registry);
    }
}
