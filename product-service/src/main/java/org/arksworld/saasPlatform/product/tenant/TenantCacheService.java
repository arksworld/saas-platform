package org.arksworld.saasPlatform.product.tenant;

import lombok.RequiredArgsConstructor;
import org.arksworld.saasPlatform.common.dto.TenantRoutingInfo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TenantCacheService {

    private final RedisTemplate<String, TenantRoutingInfo> redisTemplate;

    private static final String PREFIX = "tenant:";

    public Optional<TenantRoutingInfo> get(String tenantId) {
        return Optional.ofNullable(
                redisTemplate.opsForValue().get(PREFIX + tenantId)
        );
    }

    public void put(String tenantId, TenantRoutingInfo info) {
        redisTemplate.opsForValue().set(PREFIX + tenantId, info, Duration.ofHours(1));
    }

    public void evict(String tenantId) {
        redisTemplate.delete(PREFIX + tenantId);
    }
}