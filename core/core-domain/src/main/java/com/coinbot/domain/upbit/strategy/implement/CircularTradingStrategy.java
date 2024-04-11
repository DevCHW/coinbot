package com.coinbot.domain.upbit.strategy.implement;

import com.coinbot.domain.upbit.strategy.Strategy;

/**
 * 순환매수, 매도전략
 */
public class CircularTradingStrategy implements Strategy {

    @Override
    public void execute() {
        System.out.println("순환매 전략 실행");
    }

}
