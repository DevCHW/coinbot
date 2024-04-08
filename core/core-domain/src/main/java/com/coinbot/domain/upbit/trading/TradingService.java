package com.coinbot.domain.upbit.trading;

import com.coinbot.domain.upbit.trading.order.OrderService;
import com.coinbot.enums.OrderType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TradingService {

    private final OrderService orderService;
    private final TradingInfoManager tradingInfoManager;

    // 매수
    public void buy(String market, BigDecimal price) {
        orderService.order(market, price, null, OrderType.BUY);
    }

    // 매도
    public void sell(String market, BigDecimal quantity) {
        orderService.ordertmp(market, null, quantity, OrderType.SELL);
        orderService.order(market, null, quantity, OrderType.SELL);
    }

    // 트레이딩 정보 초기화
    public void init() {
        tradingInfoManager.init();
    }

    // 트레이딩 정보 업데이트
    public void updateInfo() {
        tradingInfoManager.update();
    }

}
