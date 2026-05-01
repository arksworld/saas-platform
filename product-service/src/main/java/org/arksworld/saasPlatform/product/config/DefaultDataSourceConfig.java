package org.arksworld.saasPlatform.product.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DefaultDataSourceConfig {

    @Bean(name = "defaultDataSource")
    public DataSource defaultDataSource() {

        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:postgresql://localhost:5432/control_db");
        ds.setUsername("postgres");
        ds.setPassword("postgres");
        ds.setDriverClassName("org.postgresql.Driver");

        ds.setMaximumPoolSize(5);
        ds.setPoolName("DefaultPool");

        return ds;
    }
}
