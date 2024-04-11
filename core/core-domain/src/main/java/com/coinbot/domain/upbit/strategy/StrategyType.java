package com.coinbot.domain.upbit.strategy;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StrategyType {
    FIVE_MINUTE_THREE_CANDLE_TRADING("myStrategy"),
    CIRCULAR_TRADING("circularTradingStrategy"),
    VOLATILITY_BREAKOUT_STRATEGY("volatilityBreakoutStrategy"),
    TEST("testStrategy");

    private final String name;
}
