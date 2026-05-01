package org.arksworld.saasPlatform.tenant.service;

import org.arksworld.saasPlatform.tenant.entity.Shard;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@Service
public class SchemaProvisioningService {

    public void createSchema(Shard shard, String schema) {
        try (Connection conn = DriverManager.getConnection(
                shard.getDbUrl(), "postgres", "postgres")) {

            Statement stmt = conn.createStatement();
            stmt.execute("CREATE SCHEMA " + schema);

        } catch (Exception e) {
            throw new RuntimeException("Schema creation failed", e);
        }
    }
}