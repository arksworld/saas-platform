package org.arksworld.saasPlatform.tenant.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "control-db")
@Data
public class ControlDbProperties {

    private String url;
    private String username;
    private String password;
}