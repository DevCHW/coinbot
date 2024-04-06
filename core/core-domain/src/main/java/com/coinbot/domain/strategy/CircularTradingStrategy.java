package com.coinbot.domain.strategy;

/**
 * 순환매수, 매도전략
 */
public class CircularTradingStrategy implements Strategy {
    @Override
    public void execute() {
        System.out.println("순환매 전략 실행");
    }
}
