package com.coinbot.client.upbit.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CandleDirection {
    UP("양봉"), DOWN("음봉");

    private final String description;
}
