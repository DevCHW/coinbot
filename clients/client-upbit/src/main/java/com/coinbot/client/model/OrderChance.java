package com.coinbot.client.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderChance {
    private BigDecimal bidFee;          // 매수 수수료 비율
    private BigDecimal askFee;          // 매도 수수료 비율
    private Market market;              // 마켓에 대한 정보
    private BidAccount bidAccount;      // 매수 시 사용하는 화폐의 계좌 상태
    private AskAccount askAccount;      // 매도 시 사용하는 화폐의 계좌 상태

    @Getter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class BidAccount {
        private String currency;             // 화폐를 의미하는 영문 대문자 코드 (코인이름 또는 화폐)
        private BigDecimal balance;          // 주문 가능 금액/수량
        private BigDecimal locked;           // 주문 중 묶여있는 금액/수량
        private BigDecimal avgBuyPrice;      // 매수평균가
        private Boolean avgBuyPriceModified; // 매수평균가 수정여부
        private String unitCurrency;         // 평단가 기준 화폐
    }

    @Getter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class AskAccount {
        private String currency;             // 화폐를 의미하는 영문 대문자 코드 (코인이름 또는 화폐)
        private BigDecimal balance;          // 주문 가능 금액/수량
        private BigDecimal locked;           // 주문 중 묶여있는 금액/수량
        private BigDecimal avgBuyPrice;      // 매수평균가
        private Boolean avgBuyPriceModified; // 매수평균가 수정여부
        private String unitCurrency;         // 평단가 기준 화폐
    }

    @Getter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Market {
        private String id;                  // 마켓의 유일 키
        private String name;                // 마켓 이름
        private List<String> askTypes;      // 매도 주문 지원 방식
        private List<String> bidTypes;      // 매수 주문 지원 방식
        private List<String> orderSides;    // 지원 주문 종류
        private Bid bid;                    // 매수 시 제약사항
        private Ask ask;                    // 매도 시 제약사항
        private BigDecimal maxTotal;        // 최대 매도/매수 금액
        private String state;               // 마켓 운영 상태

        @Getter
        @ToString
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Bid {
            private String currency;            // 화폐를 의미하는 영문 대문자 코드
            private String priceUnit;           // 주문금액 단위
            private BigDecimal minTotal;        // 최소 매도/매수 금액
        }

        @Getter
        @ToString
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Ask {
            private String currency;            // 화폐를 의미하는 영문 대문자 코드
            private String priceUnit;           // 주문금액 단위
            private BigDecimal minTotal;        // 최소 매도/매수 금액
        }

    }
}
