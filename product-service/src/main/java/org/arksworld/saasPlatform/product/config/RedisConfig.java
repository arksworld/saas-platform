package org.arksworld.saasPlatform.product.config;

import org.arksworld.saasPlatform.common.dto.TenantRoutingInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, TenantRoutingInfo> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, TenantRoutingInfo> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }
}