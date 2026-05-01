package org.arksworld.saasPlatform.tenant.repository;

import org.arksworld.saasPlatform.tenant.entity.Shard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShardRepository extends JpaRepository<Shard, String> {

    @Query("SELECT s FROM Shard s WHERE s.status = 'ACTIVE' ORDER BY s.currentLoad ASC")
    List<Shard> findAvailableShards();
}
