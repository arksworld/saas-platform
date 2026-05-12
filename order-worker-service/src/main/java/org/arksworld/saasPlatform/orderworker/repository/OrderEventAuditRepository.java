package org.arksworld.saasPlatform.orderworker.repository;

import org.arksworld.saasPlatform.orderworker.entity.OrderEventAudit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderEventAuditRepository
        extends JpaRepository<OrderEventAudit, UUID> {
}