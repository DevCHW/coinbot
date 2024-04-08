package com.coinbot.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 장 분위기
 */
@Getter
@RequiredArgsConstructor
public enum Weather {

    Sunny("맑음", "전일 종가 대비 상승한 종목이 70% 이상"),
    DRY("건조", "전일 종가 대비 상승한 종목이 60% 이상"),
    Cloudy("흐림", "전일 종가 대비 상승한 종목이 40% ~ 60%"),
    Rainy("비", "");

    private final String detail;
    private final String description;
}
