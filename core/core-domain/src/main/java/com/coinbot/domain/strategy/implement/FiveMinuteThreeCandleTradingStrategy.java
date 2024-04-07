package com.coinbot.domain.strategy.implement;

import com.coinbot.client.upbit.UpbitClient;
import com.coinbot.client.upbit.model.Candle;
import com.coinbot.client.upbit.model.Ticker;
import com.coinbot.client.upbit.param.MinuteCandleParam;
import com.coinbot.domain.strategy.Strategy;
import com.coinbot.domain.trading.TradingInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

import static org.springframework.util.StringUtils.*;

/**
 * 5분봉 3틱 매수 전략
 */
@Component
@RequiredArgsConstructor
public class FiveMinuteThreeCandleTradingStrategy implements Strategy {

    private final UpbitClient upbit;
    private static final BigDecimal buyPercentage = new BigDecimal("0.2"); // 진입 시 현재 시드의 퍼센테이지 만큼 매수

    @Override
    public void execute() {
        String target = findTarget(); // 매수 타이밍인 종목 검색
        if (hasText(target)) {
            BigDecimal price = TradingInfo.seedMoney().multiply(buyPercentage);
//            tradingService.buy(target, price); // 매수 주문
        }
    }

    // 5분봉 3틱 전략 조건에 맞는 매수 종목 검색
    private String findTarget() {
        // 모든 Ticker를 조회한다.
        List<Ticker> tickers = upbit.getTickers(TradingInfo.marketList());

        // 24시간 기준 거래대금 Top 5 종목을 필터링한다.
        List<Ticker> top5Volume24h = tickers.stream()
                .sorted(Comparator.comparing(Ticker::getAccTradePrice24h).reversed())
                .limit(10)
                .toList();

        // 루프를 돌며 5분봉 10캔들 개를 조회한다.
        for (Ticker ticker : top5Volume24h) {
            String market = ticker.getMarket();
            MinuteCandleParam candleParam = MinuteCandleParam.builder()
                    .unit(5)        // 캔들 단위
                    .market(market) // 캔들 종목
                    .count(10)       // 가져올 캔들의 수
                    .build();
            List<Candle> minuteCandles = upbit.getCandles(candleParam);
            boolean isTiming = false;
            for (Candle minuteCandle : minuteCandles) {

                System.out.println("minuteCandle = " + minuteCandle);
                // 전체 길이..?
            }
            // 5분봉 3캔들이 음봉이며 매수하기에 적합한지 검사한다.

        }

        return top5Volume24h.get(0).getMarket();
    }
}
