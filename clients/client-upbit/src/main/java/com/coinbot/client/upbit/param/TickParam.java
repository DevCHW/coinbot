package com.coinbot.client.upbit.param;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TickParam {
    private String market;          // 마켓 코드
    private LocalDateTime to;
    private Integer count;
    private String cursor;
    private Integer daysAgo;        //

    @Builder
    public TickParam(String market, LocalDateTime to, Integer count, String cursor, Integer daysAgo) {
        this.market = market;
        this.to = to;
        this.count = count;
        this.cursor = cursor;
        this.daysAgo = daysAgo;
    }
}
