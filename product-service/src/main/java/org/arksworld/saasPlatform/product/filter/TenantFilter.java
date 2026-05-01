package org.arksworld.saasPlatform.product.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.arksworld.saasPlatform.common.dto.TenantContext;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

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

            chain.doFilter(request, response);

        } finally {
            TenantContext.clear();
        }
    }
}