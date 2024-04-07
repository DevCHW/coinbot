package com.coinbot.client.upbit.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Market {
    private String market;              // 업비트에서 제공중인 시장 정보
    private String koreanName;          // 거래 대상 디지털 자산 한글명
    private String englishName;         // 거래 대상 디지털 자산 영문명
    private String marketWarning;       // 유의 종목 여부 (NONE : 해당 사항 없음/CAUTION : 투자 유의)
    private Event marketEvent;

    @Getter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    static class Event {
        private Boolean warning;        // 업비트 시장경보 유의종목 지정 여부
        private Caution caution;

        @Getter
        @ToString
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        static class Caution {
            @JsonProperty("PRICE_FLUCTUATIONS")
            private Boolean priceFluctuations;              // 가격 급등락 경보 발령 여부

            @JsonProperty("TRADING_VOLUME_SOARING")
            private Boolean tradingVolumeSoaring;           // 거래량 급등 경보 발령 여부

            @JsonProperty("DEPOSIT_AMOUNT_SOARING")
            private Boolean depositAmountSoaring;           // 입금량 급등 경보 발령 여부

            @JsonProperty("GLOBAL_PRICE_DIFFERENCES")
            private Boolean globalPriceDifferences;         // 가격 차이 경보 발령 여부

            @JsonProperty("CONCENTRATION_OF_SMALL_ACCOUNTS")
            private Boolean concentrationOfSmallAccounts;   // 소수 계정 집중 경보 발령 여부
        }
    }
}
