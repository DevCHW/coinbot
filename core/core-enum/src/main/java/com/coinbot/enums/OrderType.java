package com.coinbot.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderType {

    BUY("매수"), SELL("매도");

    private final String description;
}
