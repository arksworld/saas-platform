package org.arksworld.saasPlatform.tenant.resolver;

public interface TenantResolver {

    String resolveShard(String tenantId);

    String resolveSchema(String tenantId);
}