INSERT INTO shards VALUES (
  'shard1',
  'jdbc:postgresql://localhost:5433/shard_db_1',
  100,
  0,
  'ACTIVE'
);

COMMIT;