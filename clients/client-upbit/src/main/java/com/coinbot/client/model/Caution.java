package com.coinbot.client.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Caution {
    PRICE_FLUCTUATIONS("가격 급등락 경보 발령 여부"),
    TRADING_VOLUME_SOARING("거래량 급등 경보 발령 여부"),
    DEPOSIT_AMOUNT_SOARING("입금량 급등 경보 발령 여부"),
    GLOBAL_PRICE_DIFFERENCES("가격 차이 경보 발령 여부"),
    CONCENTRATION_OF_SMALL_ACCOUNTS("소수 계정 집중 경보 발령 여부");

    private final String description;
}
