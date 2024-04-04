package com.coinbot.storage.db.core.repository;

import com.coinbot.storage.db.core.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
