package com.coinbot.client.upbit.model;

import com.coinbot.client.upbit.model.enums.CandleDirection;
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
    private BigDecimal prevClosingPrice;           // 전일 종가(UTC 0시 기준)
    private BigDecimal changePrice;                // 전일 종가 대비 변화 금액
    private BigDecimal changeRate;                 // 전일 종가 대비 변화량
    private BigDecimal convertedTradePrice;        // 종가 환산 화폐 단위로 환산된 가격(요청에 convertingPriceUnit 파라미터 없을 시 해당 필드 포함되지 않음.)
    private String firstDayOfPeriod;               // 캔들 기간의 가장 첫 날


    // 캔들의 길이 (고가 - 저가)
    public BigDecimal length() {
        return highPrice.subtract(lowPrice);
    }

    // 방향
    public CandleDirection direction(Candle before) {
        return before.getTradePrice().compareTo(tradePrice) < 0 ? CandleDirection.UP : CandleDirection.DOWN;
    }
}
