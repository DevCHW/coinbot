package com.coinbot.client.upbit.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrdType {
    LIMIT("limit", "지정가 주문"),
    PRICE("price", "시장가 주문(매수)"),
    MARKET("market", "시장가 주문(매도)"),
    BEST("best", "최유리 주문");

    private final String value;
    private final String description;

}
