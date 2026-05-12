package org.arksworld.saasPlatform.tenant.repository;


import org.arksworld.saasPlatform.tenant.entity.TenantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<TenantEntity, String> {
}