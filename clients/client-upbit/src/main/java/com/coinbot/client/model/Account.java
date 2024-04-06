package com.coinbot.client.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Account {
    private String currency;             // 화폐를 의미하는 영문 대문자 코드 (코인이름 또는 화폐)
    private BigDecimal balance;          // 주문 가능 금액/수량
    private Long locked;                 // 주문 중 묶여있는 금액/수량
    private Long avgBuyPrice;            // 매수평균가
    private Boolean avgBuyPriceModified; // 매수평균가 수정여부
    private String unitCurrency;         // 평단가 기준 화폐
}
