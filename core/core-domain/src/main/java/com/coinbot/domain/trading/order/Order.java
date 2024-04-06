package com.coinbot.domain.trading.order;

import com.coinbot.enums.OrderType;
import com.coinbot.storage.db.core.entity.OrderEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Order {

    private Long id;
    private String uuid;
    private String name;
    private OrderType orderType;

    public OrderEntity toEntity() {
        return OrderEntity.builder().build();
    }
}
