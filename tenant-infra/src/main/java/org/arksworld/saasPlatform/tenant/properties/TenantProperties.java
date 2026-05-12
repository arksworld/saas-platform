package org.arksworld.saasPlatform.tenant.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@Data
@ConfigurationProperties(prefix = "tenant")
public class TenantProperties {

    private Map<String, ShardProperties> shards = new HashMap<>();

    @Data
    public static class ShardProperties {

        private String url;
        private String username;
        private String password;
    }
}