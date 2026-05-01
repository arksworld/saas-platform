package org.arksworld.saasPlatform.auth.user.dto;

import lombok.Data;

@Data
public class UserRequest {
    private String id;

    private String username;

    private String password;

    private String tenantId;

    private String role;
}
