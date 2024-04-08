package com.coinbot.domain.upbit.strategy;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StrategyType {
    FIVE_MINUTE_THREE_CANDLE_TRADING("fiveMinuteThreeCandleTradingStrategy"),
    CIRCULAR_TRADING("circularTradingStrategy"),
    TEST("testStrategy");

    private final String name;
}
