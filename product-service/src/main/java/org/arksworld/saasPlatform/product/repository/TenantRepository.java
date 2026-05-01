package org.arksworld.saasPlatform.product.repository;

import org.arksworld.saasPlatform.product.entity.TenantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<TenantEntity, String> {
}