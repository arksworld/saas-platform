package org.arksworld.saasPlatform.tenant.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class ControlDbConfig {

   @Bean(name = "controlDataSource")
   @ConfigurationProperties(prefix = "control.datasource")
    public DataSource controlDataSource() {
       log.info("Entering controlDataSource");
        return DataSourceBuilder.create()
                .type(com.zaxxer.hikari.HikariDataSource.class)
               .build();
    }

    @Bean(name="controlJdbcTemplate")
    public JdbcTemplate controlJdbcTemplate(
            @Qualifier("controlDataSource") DataSource ds) {
        log.info("Entering controlJdbcTemplate");
        return new JdbcTemplate(ds);
    }
}
