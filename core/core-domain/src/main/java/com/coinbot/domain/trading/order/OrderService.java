package com.coinbot.domain.trading.order;

import com.coinbot.client.upbit.UpbitClient;
import com.coinbot.client.upbit.model.enums.OrdType;
import com.coinbot.client.upbit.model.Order;
import com.coinbot.client.upbit.model.enums.Side;
import com.coinbot.client.upbit.param.OrderParam;
import com.coinbot.enums.OrderType;
import com.coinbot.storage.db.core.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final UpbitClient upbit;
    private final OrderRepository orderRepository;

    // 주문
    public void order(String market, BigDecimal price, BigDecimal volume, OrderType orderType) {
        // 매수
        if (orderType == OrderType.BUY) {
            OrderParam param = OrderParam.builder()
                    .market(market)
                    .side(Side.BID)
                    .ordType(OrdType.PRICE)   // 시장가 매수
                    .price(price)
                    .build();
            Order order = upbit.order(param);
        }

        // 매도
        if (orderType == OrderType.SELL) {
            OrderParam param = OrderParam.builder()
                    .market(market)
                    .side(Side.ASK)
                    .volume(volume)            // 전량 매도
                    .ordType(OrdType.MARKET)   // 시장가 매도
                    .build();
            Order order = upbit.order(param);
        }
    }

    // 주문
    public void ordertmp(String market, BigDecimal price, BigDecimal volume, OrderType orderType) {
        // 매수
        if (orderType == OrderType.BUY) {
            OrderParam param = OrderParam.builder()
                    .market(market)
                    .side(Side.BID)
                    .ordType(OrdType.PRICE)   // 시장가 매수
                    .price(price)
                    .build();
            Order result = upbit.order(param);
        }

        // 매도
        if (orderType == OrderType.SELL) {
            OrderParam param = OrderParam.builder()
                    .market(market)
                    .side(Side.ASK)
                    .volume(volume)            // 전량 매도
                    .ordType(OrdType.MARKET)   // 시장가 매도
                    .build();
            upbit.testOrder(param);
        }
    }

}
