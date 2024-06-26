package com.coinbot.client.upbit.param;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MinuteCandleParam {
    private int unit;               // 캔들 단위 (ex. 5일 경우 5분봉)
    private String market;          // 마켓 코드 (ex. KRW-BTC)
    private LocalDateTime to;       // 마지막 캔들 시각 (exclusive). ISO8061 포맷 (yyyy-MM-dd'T'HH:mm:ss'Z' or yyyy-MM-dd HH:mm:ss). 기본적으로 UTC 기준 시간이며 2023-01-01T00:00:00+09:00 과 같이 KST 시간으로 요청 가능. 비워서 요청시 가장 최근 캔들
    private Integer count;          // 캔들 개수(최대 200개까지 요청 가능)

    @Builder
    public MinuteCandleParam(int unit, String market, LocalDateTime to, Integer count) {
        this.unit = unit;
        this.market = market;
        this.to = to;
        this.count = count;
    }
}
