package com.coinbot.domain.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderType {

    BUY("매수"), SELL("매도");

    private final String description;
}
