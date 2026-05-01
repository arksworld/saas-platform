package org.arksworld.saasPlatform.product.config;

import org.arksworld.saasPlatform.product.datasource.DataSourceRegistry;
import org.arksworld.saasPlatform.product.datasource.DynamicDataSourceRouter;
import org.arksworld.saasPlatform.product.tenant.TenantRoutingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    @Primary
    public DataSource dataSource(
            TenantRoutingService routingService,
            DataSourceRegistry registry) {

        System.out.println("Using DynamicDataSourceRouter...............");

        return new DynamicDataSourceRouter(routingService, registry);
    }
}
