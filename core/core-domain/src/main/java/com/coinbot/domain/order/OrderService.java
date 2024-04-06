package com.coinbot.domain.order;

import com.coinbot.client.model.Order;
import com.coinbot.storage.db.core.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    // 주문 내역 저장
    public void save(Order order) {

    }

}
