package org.arksworld.saasPlatform.tenant.datasource;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Slf4j
@Component
public class DataSourceFactory {

    public DataSource create(String dbUrl) {

        log.info("Create: "+ dbUrl);
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