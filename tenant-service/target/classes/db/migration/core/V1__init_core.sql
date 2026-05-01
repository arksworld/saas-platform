CREATE TABLE tenants (
    tenant_id VARCHAR PRIMARY KEY,
    tenant_name VARCHAR,
    status VARCHAR,
    tier VARCHAR,
    shard_id VARCHAR,
    schema_name VARCHAR,
    db_url VARCHAR,
    created_at TIMESTAMP
);

CREATE TABLE shards (
    shard_id VARCHAR PRIMARY KEY,
    db_url VARCHAR,
    max_capacity INT,
    current_load INT,
    status VARCHAR
);