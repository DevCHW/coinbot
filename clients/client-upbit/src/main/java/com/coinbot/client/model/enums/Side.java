package com.coinbot.client.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Side {
    BID("bid", "매수"), ASK("ask", "매도");
    
    private final String value;
    private final String description;
}
