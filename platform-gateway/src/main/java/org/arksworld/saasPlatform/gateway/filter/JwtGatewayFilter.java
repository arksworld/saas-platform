package org.arksworld.saasPlatform.gateway.filter;

import lombok.RequiredArgsConstructor;
import org.arksworld.saasPlatform.common.jwt.JwtUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtGatewayFilter implements GlobalFilter {



    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();

        // Skip auth endpoints
        if (request.getURI().getPath().startsWith("/auth")) {
            return chain.filter(exchange);
        }

        String authHeader = request.getHeaders().getFirst("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Missing token");
        }

        String token = authHeader.substring(7);

        String tenantId = JwtUtil.getTenantId(token);
        String username = JwtUtil.getUsername(token);

        // Inject headers
        ServerHttpRequest mutatedRequest = request.mutate()
                .header("X-Tenant-ID", tenantId)
                .header("X-User", username)
                .build();

        return chain.filter(exchange.mutate().request(mutatedRequest).build());
    }
}