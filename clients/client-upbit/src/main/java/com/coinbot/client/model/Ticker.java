package com.coinbot.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Ticker {

    private String market;                    // 종목 구분 코드
    private String tradeDate;                 // 최근 거래 일자(UTC / 포맷: yyyyMMdd)
    private String tradeTime;                 // 최근 거래 시각(UTC / 포맷: HHmmss)
    private String tradeDateKst;              // 최근 거래 일자(KST / 포맷 : yyyyMMdd)
    private String tradeTimeKst;              // 최근 거래 시각(KST / 포맷 : HHmmss)
    private Long tradeTimestamp;              // 최근 거래 일시(UTC / 포맷 Unix Timestamp)
    private BigDecimal openingPrice;          // 시가
    private BigDecimal highPrice;             // 고가
    private BigDecimal lowPrice;              // 저가
    private BigDecimal tradePrice;            // 종가(현재가)
    private BigDecimal prevClosingPrice;      // 전일 종가(UTC 0시 기준)
    private String change;                    // EVEN : 보합 / RISE : 상승 / FALL : 하락
    private BigDecimal changePrice;           // 변화액의 절대값
    private BigDecimal changeRate;            // 변화율의 절대값
    private BigDecimal signedChangePrice;     // 부호가 있는 변화액
    private BigDecimal signedChangeRate;      // 부호가 있는 변화율
    private BigDecimal tradeVolume;           // 가장 최근 거래량
    private BigDecimal accTradePrice;         // 누적 거래대금(UTC 0시 기준)
    @JsonProperty("acc_trade_price_24h")
    private BigDecimal accTradePrice24h;      // 24시간 누적 거래대금
    private BigDecimal accTradeVolume;        // 누적 거래량(UTC 0시 기준)
    @JsonProperty("acc_trade_volume_24h")
    private BigDecimal accTradeVolume24h;     // 24시간 누적 거래량
    @JsonProperty("highest_52_week_price")
    private BigDecimal highest52WeekPrice;    // 52주 신고가
    @JsonProperty("highest_52_week_date")
    private LocalDate highest52WeekDate;      // 52주 신고가 달성일 (포맷: yyyy-MM-dd)
    @JsonProperty("lowest_52_week_price")
    private BigDecimal lowest52WeekPrice;     // 52주 신저가
    @JsonProperty("lowest_52_week_date")
    private LocalDate lowest52WeekDate;       // 52주 신저가 달성일 (포맷: yyyy-MM-dd)
    private Long timestamp;                   // 타임스탬프

    // 위 응답의 change, changePrice, changeRate, signedChangePrice, signedChangeRate 필드들은 전일종가 대비 값입니다.

    @Override
    public String toString() {
        return  "market='" + market + "\n" +
                "tradeDate='" + tradeDate  + "\n" +
                "tradeTime='" + tradeTime  + "\n" +
                "tradeDateKst='" + tradeDateKst  + "\n" +
                "tradeTimeKst='" + tradeTimeKst  + "\n" +
                "tradeTimestamp=" + tradeTimestamp  + "\n" +
                "openingPrice=" + openingPrice  + "\n" +
                "highPrice=" + highPrice  + "\n" +
                "lowPrice=" + lowPrice  + "\n" +
                "tradePrice=" + tradePrice  + "\n" +
                "prevClosingPrice=" + prevClosingPrice  + "\n" +
                "change='" + change  + "\n" +
                "changePrice=" + changePrice  + "\n" +
                "changeRate=" + changeRate  + "\n" +
                "signedChangePrice=" + signedChangePrice  + "\n" +
                "signedChangeRate=" + signedChangeRate  + "\n" +
                "tradeVolume=" + tradeVolume  + "\n" +
                "accTradePrice=" + accTradePrice  + "\n" +
                "accTradePrice24h=" + accTradePrice24h  + "\n" +
                "accTradeVolume=" + accTradeVolume  + "\n" +
                "accTradeVolume24h=" + accTradeVolume24h  + "\n" +
                "highest52WeekPrice=" + highest52WeekPrice  + "\n" +
                "highest52WeekDate=" + highest52WeekDate  + "\n" +
                "lowest52WeekPrice=" + lowest52WeekPrice  + "\n" +
                "lowest52WeekDate=" + lowest52WeekDate  + "\n" +
                "timestamp=" + timestamp + "\n" ;
    }
}
