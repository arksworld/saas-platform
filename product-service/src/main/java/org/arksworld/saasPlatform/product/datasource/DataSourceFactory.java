package org.arksworld.saasPlatform.product.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DataSourceFactory {

    public DataSource create(String dbUrl) {

        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(dbUrl);
        ds.setUsername("postgres");
        ds.setPassword("postgres");

        ds.setMaximumPoolSize(10);
        ds.setMinimumIdle(2);
        ds.setPoolName("ShardPool-" + dbUrl);

        return ds;
    }
}