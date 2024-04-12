package com.coinbot.domain.upbit.strategy.implement;

import com.coinbot.client.upbit.UpbitClient;
import com.coinbot.client.upbit.model.Candle;
import com.coinbot.client.upbit.model.Ticker;
import com.coinbot.client.upbit.param.MinuteCandleParam;
import com.coinbot.domain.upbit.strategy.Strategy;
import com.coinbot.domain.upbit.trading.TradingInfo;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

/**
 * 변동성 돌파 전략
 */
@Component
@RequiredArgsConstructor
public class VolatilityBreakoutStrategy implements Strategy {

    private final UpbitClient upbit;

    @Override
    public void execute() {
        String targetMarket = findTarget(); // 매수 타이밍인 종목 검색
    }

    // 전략에 맞는 종목 검색
    private String findTarget() {
        // 모든 Ticker를 조회한다.
        List<Ticker> tickers = upbit.getTickers(TradingInfo.marketList());

        // 24시간 기준 거래대금 Top 5 종목을 필터링한다.
        List<Ticker> top5Volume24h = tickers.stream()
                .sorted(Comparator.comparing(Ticker::getAccTradePrice24h).reversed())
                .limit(10)
                .toList();

        for (Ticker ticker : top5Volume24h) {
            int k = 5;
            String market = ticker.getMarket();
            MinuteCandleParam param = MinuteCandleParam.builder()
                    .market(market)
                    .count(1)
                    .build();
            Candle candle = upbit.getCandle(param);
            System.out.println("candle = " + candle);
        }
//        BigDecimal newLength = upbit.getCandle();
        return null;
    }

}
