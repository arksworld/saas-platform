package org.arksworld.saasPlatform.tenant.config;


import org.arksworld.saasPlatform.common.dto.TenantContext;
import org.arksworld.saasPlatform.tenant.filter.TenantFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ConditionalOnClass(DataSource.class)
public class TenantAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public TenantContext tenantContext() {
        return new TenantContext();
    }

    @Bean
    @ConditionalOnMissingBean
    public TenantFilter tenantFilter() {
        return new TenantFilter();
    }
}