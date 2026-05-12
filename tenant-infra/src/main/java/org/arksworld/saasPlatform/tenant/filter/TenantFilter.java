package org.arksworld.saasPlatform.tenant.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.arksworld.saasPlatform.common.dto.TenantContext;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class TenantFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        try {
            String tenantId = request.getHeader("X-Tenant-ID");
            TenantContext.set(tenantId);
            MDC.put("tenantId", tenantId);
            log.info("HTTP Tenant Set: {}", tenantId);

            chain.doFilter(request, response);

        } finally {
            TenantContext.clear();
        }
    }
}
