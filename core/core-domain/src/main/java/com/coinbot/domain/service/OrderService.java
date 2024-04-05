package com.coinbot.domain.service;

import com.coinbot.storage.db.core.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    // 주문하기
    public void order() {

    }

}
