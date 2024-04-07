package com.coinbot.domain.trading;

import com.coinbot.domain.trading.order.OrderService;
import com.coinbot.enums.OrderType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TradingService {

    private final OrderService orderService;
    private final TradingInfoInitializer initializer;

    // 매수
    public void buy(String market, BigDecimal price) {
        orderService.order(market, price, null, OrderType.BUY);
    }

    // 매도
    public void sell(String market, BigDecimal quantity) {
        orderService.order(market, null, quantity, OrderType.SELL);
    }

    // 매도
    public void sellTmp(String market, BigDecimal quantity) {
        orderService.orderTest(market, null, quantity, OrderType.SELL);
    }
    
    // 트레이딩 정보 새로고침
    public void tradingInfoReload() {
        initializer.initialize();
    }

}
