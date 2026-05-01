CREATE TABLE users (
    id VARCHAR PRIMARY KEY,
    username VARCHAR,
    password VARCHAR,
    tenant_id VARCHAR,
    role VARCHAR,
    UNIQUE(username, tenant_id)
);