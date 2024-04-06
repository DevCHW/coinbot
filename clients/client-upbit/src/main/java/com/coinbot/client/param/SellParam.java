package com.coinbot.client.param;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SellParam {
    private String market;                  // 마켓 ID(필수)
    private BigDecimal volume;                  //
    private String side;                    // 주문 종류(필수)
    private String ordType;                 // 주문 타입(필수) limit / price / market / best(time_in_force)
    private Long price;                     // 주문 가격.(지정가, 시장가 매수 시 필수) ex) KRW-BTC 마켓에서 1BTC당 1,000 KRW로 거래할 경우, 값은 1000이 된다. ex) KRW-BTC 마켓에서 1BTC당 매도 1호가 500KRW인 경우, 시장가 매수 시 값을 1000으로 세팅하면 2BTC가 매수된다.(수수료가 존재하거나 매도 1호가의 수량에 따라 상이할 수 있음)

    @Builder
    public SellParam(String market, BigDecimal volume, String side, String ordType, Long price) {
        this.market = market;
        this.volume = volume;
        this.side = side;
        this.ordType = ordType;
        this.price = price;
    }
}
