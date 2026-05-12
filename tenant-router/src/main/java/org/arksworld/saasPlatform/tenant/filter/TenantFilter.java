package org.arksworld.saasPlatform.tenant.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.arksworld.saasPlatform.tenant.dto.TenantContext;
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
            log.info("Got tenantId:{}", tenantId);
            TenantContext.setCurrentTenant(tenantId);

            chain.doFilter(request, response);

        } finally {
            TenantContext.clear();
        }
    }
}