package org.arksworld.saasPlatform.tenant.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Slf4j
@Configuration
@EnableConfigurationProperties(ControlDbProperties.class)
@RequiredArgsConstructor
public class ControlDataSourceConfig {

    private final ControlDbProperties props;

    @Bean(name = "controlDataSource")
    public DataSource controlDataSource() {

        log.info("Initiating controlDataSource..{}", props.getUrl());
        HikariDataSource ds =
                new HikariDataSource();

        ds.setJdbcUrl(props.getUrl());
        ds.setUsername(props.getUsername());
        ds.setPassword(props.getPassword());

        return ds;
    }

    @Bean(name = "controlJdbcTemplate")
    public JdbcTemplate controlJdbcTemplate(
            @Qualifier("controlDataSource")
            DataSource ds) {
        log.info("Inside controlJdbcTemplate ");
        return new JdbcTemplate(ds);
    }
}