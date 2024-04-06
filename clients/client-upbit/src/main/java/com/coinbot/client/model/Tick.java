package com.coinbot.client.model;

import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@ToString
public class Tick {
    private String market;                  // 마켓 구분 코드
    private LocalDate tradeDateUtc;         // 체결 일자(UTC 기준 포맷 : yyyy-MM-dd)
    private LocalTime tradeTimeUtc;         // 체결 시각(UTC 기준 포맷 : HH:mm:ss)
    private Long timestamp;                 // 체결 타임스탬프
    private BigDecimal tradePrice;          // 체결 가격
    private BigDecimal tradeVolume;         // 체결량
    private BigDecimal prevClosingPrice;    // 전일 종가(UTC 0시 기준)
    private BigDecimal changePrice;         // 변화량
    private String askBid;                  // 매도/매수
    private Long sequentialId;              // 체결 번호 (Unique)
}
