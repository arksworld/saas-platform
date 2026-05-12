package org.arksworld.saasPlatform.order.repository;

import org.arksworld.saasPlatform.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, String>{
}