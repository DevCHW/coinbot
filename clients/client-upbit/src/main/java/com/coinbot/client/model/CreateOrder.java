package com.coinbot.client.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@NoArgsConstructor
public class CreateOrder {

    private String market;                  // 마켓 ID(필수)
    private Side side;                      // 주문 종류(필수)
    private Double volume;                  // 주문량 (지정가, 시장가 매도 시 필수)
    private OrderType orderType;            // 주문 타입(필수) limit / price / market / best(time_in_force)
    private Long price;                     // 주문 가격.(지정가, 시장가 매수 시 필수) ex) KRW-BTC 마켓에서 1BTC당 1,000 KRW로 거래할 경우, 값은 1000이 된다. ex) KRW-BTC 마켓에서 1BTC당 매도 1호가 500KRW인 경우, 시장가 매수 시 값을 1000으로 세팅하면 2BTC가 매수된다.(수수료가 존재하거나 매도 1호가의 수량에 따라 상이할 수 있음)
    private String identifier;              // 조회용 사용자 지정값(선택)
    private String timeInForce;             // IOC, FOK 주문 설정 * -ioc : Immediate or Cancel - fok : Fill or KILL

    @Builder
    public CreateOrder(String market, Side side, Double volume, OrderType orderType, Long price, String identifier, String timeInForce) {
        this.market = market;
        this.side = side;
        this.volume = volume;
        this.orderType = orderType;
        this.price = price;
        this.identifier = identifier;
        this.timeInForce = timeInForce;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> params = new ConcurrentHashMap<>();
        params.put("market", this.market);
        params.put("side", this.side.getValue());
        params.put("volume", this.getVolume());
        params.put("price", this.getPrice());
        params.put("ord_type", this.getOrderType().getValue());
        return params;
    }
}
