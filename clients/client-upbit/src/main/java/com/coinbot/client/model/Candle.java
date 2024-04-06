package com.coinbot.client.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Candle {
    private String market;                         // 마켓명
    private LocalDateTime candleDateTimeUtc;       // 캔들 기준 시각(UTC 기준) 포맷: yyyy-MM-dd'T'HH:mm:ss
    private LocalDateTime candleDateTimeKst;       // 캔들 기준 시각(KST 기준) 포맷: yyyy-MM-dd'T'HH:mm:ss
    private BigDecimal openingPrice;               // 시가
    private BigDecimal highPrice;                  // 고가
    private BigDecimal lowPrice;                   // 저가
    private BigDecimal tradePrice;                 // 종가
    private Long timestamp;                        // 해당 캔들에서 마지막 틱이 저장된 시각
    private BigDecimal candleAccTradePrice;        // 누적 거래 금액
    private BigDecimal candleAccTradeVolume;       // 누적 거래량
    private Integer unit;                          // 분 단위(유닛)
}
