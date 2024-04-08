package com.coinbot.client.upbit.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Change {

    EVEN("보합"), RISE("상승"), FALL("하락");

    private final String description;
}
