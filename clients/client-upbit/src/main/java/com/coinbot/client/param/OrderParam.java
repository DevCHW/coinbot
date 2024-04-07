package com.coinbot.client.param;

import com.coinbot.client.model.enums.OrdType;
import com.coinbot.client.model.enums.Side;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderParam {
    private String market;                  // 마켓 ID(필수)
    private String side;                    // 주문 종류(필수)
    private BigDecimal volume;                  // 주문량 (지정가, 시장가 매도 시 필수)
    private String ordType;                 // 주문 타입(필수) limit / price / market / best(time_in_force)
    private BigDecimal price;               // 주문 가격.(지정가, 시장가 매수 시 필수) ex) KRW-BTC 마켓에서 1BTC당 1,000 KRW로 거래할 경우, 값은 1000이 된다. ex) KRW-BTC 마켓에서 1BTC당 매도 1호가 500KRW인 경우, 시장가 매수 시 값을 1000으로 세팅하면 2BTC가 매수된다.(수수료가 존재하거나 매도 1호가의 수량에 따라 상이할 수 있음)

    @Builder
    public OrderParam(String market, Side side, BigDecimal volume, OrdType ordType, BigDecimal price) {
        this.market = market;
        this.side = side.getValue();
        this.volume = side == Side.ASK ? volume : null;
        this.price = side == Side.BID ? price : null;
        this.ordType = ordType.getValue();
    }

}
