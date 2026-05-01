package org.arksworld.saasPlatform.tenant.controller;

import lombok.RequiredArgsConstructor;
import org.arksworld.saasPlatform.tenant.dto.TenantRequest;
import org.arksworld.saasPlatform.tenant.dto.TenantResponse;
import org.arksworld.saasPlatform.tenant.service.TenantService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tenants")
@RequiredArgsConstructor
public class TenantController {

    private final TenantService tenantService;

    @PostMapping
    public TenantResponse create(@RequestBody TenantRequest request) {
        return tenantService.createTenant(request);
    }
}