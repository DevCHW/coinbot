package com.coinbot.domain.trading;

import com.coinbot.client.UpbitClient;
import com.coinbot.client.model.Account;
import com.coinbot.client.model.Coin;
import com.coinbot.client.model.Order;
import com.coinbot.client.model.OrderType;
import com.coinbot.client.param.BuyParam;
import com.coinbot.domain.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static com.coinbot.domain.trading.TradingInfo.*;

@Service
@RequiredArgsConstructor
public class TradingService {

    private final UpbitClient upbit;

    // 매수
    public void buy(String market, BigDecimal price) {
        BuyParam param = BuyParam.builder()
                .market(market)
                .ordType(OrderType.PRICE)   //시장가 매수
                .price(price)
                .build();
        Order order = upbit.buy(param);
        System.out.println("order = " + order); // 주문 결과 출력
    }

    // 매도
    public void sell(String market) {

    }

}
