package com.coinbot.domain.trading.order;

import com.coinbot.client.UpbitClient;
import com.coinbot.client.model.Order;
import com.coinbot.client.model.OrdType;
import com.coinbot.client.param.OrderParam;
import com.coinbot.client.param.SellParam;
import com.coinbot.domain.trading.TradingInfo;
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

    public void order(String market, BigDecimal price, OrderType orderType) {
        // 매수
        if (orderType == OrderType.BUY) {
            OrderParam param = OrderParam.builder()
                    .market(market)
                    .ordType(OrdType.PRICE)   //시장가 매수
                    .price(price)
                    .build();
            Order order = upbit.buy(param);
        }

        // 매도
        if (orderType == OrderType.SELL) {
            OrderParam.builder()
                    .market(market)
                    .ordType(OrdType.PRICE)   //시장가 매수
                    .price(price)
                    .build();
        }
    }

    // 매도
    public void sell(String market) {

    }

    // 주문 내역 저장
    public void save(Order order) {

    }

}
