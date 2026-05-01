package org.arksworld.saasPlatform.tenant.service;

import org.arksworld.saasPlatform.tenant.entity.Shard;
import org.arksworld.saasPlatform.tenant.repository.ShardRepository;
import org.springframework.stereotype.Service;

@Service
public class ShardAllocator {

    private final ShardRepository shardRepository;

    public ShardAllocator(final ShardRepository shardRepository) {
        this.shardRepository = shardRepository;
    }

    public Shard allocateShard() {
        return shardRepository.findAvailableShards()
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No shard available"));
    }
}