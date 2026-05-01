package org.arksworld.saasPlatform.product.datasource;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class DataSourceRegistry {

    private final Map<String, DataSource> dataSources = new ConcurrentHashMap<>();

    private final DataSourceFactory dataSourceFactory;

    public DataSource getDataSource(String shardId, String dbUrl) {

        return dataSources.computeIfAbsent(shardId, key -> {
            return dataSourceFactory.create(dbUrl);
        });
    }
}
