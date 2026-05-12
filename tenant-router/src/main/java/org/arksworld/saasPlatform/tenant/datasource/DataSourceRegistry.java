package org.arksworld.saasPlatform.tenant.datasource;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataSourceRegistry {

    private final Map<String, DataSource> dataSources = new ConcurrentHashMap<>();

    private final DataSourceFactory dataSourceFactory;

    public DataSource getDataSource(String shardId, String dbUrl) {
        log.info("getDataSource: Shard:{}, dbUrl:{}", shardId, dbUrl);
        return dataSources.computeIfAbsent(shardId, key -> {
            return dataSourceFactory.create(dbUrl);
        });
    }
}
