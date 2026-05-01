package org.arksworld.saasPlatform.tenant.service;

import org.arksworld.saasPlatform.tenant.entity.Shard;
import org.flywaydb.core.Flyway;
import org.springframework.stereotype.Service;

@Service
public class MigrationService {

    public void migrate(Shard shard, String schema) {

        Flyway flyway = Flyway.configure()
                .dataSource(shard.getDbUrl(), "postgres", "postgres")
                .schemas(schema)
                .load();

        flyway.migrate();
    }

    public void migrateCore(Shard shard, String schema) {
        runFlyway(shard, schema, "db/migration/core");
    }

    public void migrateProduct(Shard shard, String schema) {
        runFlyway(shard, schema, "db/migration/product");
    }

    private void runFlyway(Shard shard, String schema, String location) {

        Flyway flyway = Flyway.configure()
                .dataSource(shard.getDbUrl(), "postgres", "postgres")
                .schemas(schema)
                .locations("classpath:" + location)
                .load();

        flyway.migrate();
    }


    public void migrateALl(Shard shard, String schema) {

        Flyway flyway = Flyway.configure()
                .dataSource(shard.getDbUrl(), "postgres", "postgres")
                .schemas(schema)
                .locations(
                        "classpath:db/migration/core",
                        "classpath:db/migration/product"
                )
                .load();

        flyway.migrate();
    }
}