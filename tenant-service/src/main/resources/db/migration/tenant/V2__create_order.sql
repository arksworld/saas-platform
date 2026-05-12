CREATE TABLE orders (
    id VARCHAR(255) PRIMARY KEY,
    user_id VARCHAR(255),
    amount DOUBLE PRECISION,
    status VARCHAR(255)
);


CREATE TABLE order_event_audit (
    id UUID PRIMARY KEY,
    order_id VARCHAR(255),
    event_type VARCHAR(100),
    processed_at TIMESTAMP
);