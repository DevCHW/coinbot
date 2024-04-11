package com.coinbot.domain.upbit.strategy.implement;

import com.coinbot.client.upbit.UpbitClient;
import com.coinbot.client.upbit.model.Candle;
import com.coinbot.client.upbit.model.Ticker;
import com.coinbot.client.upbit.model.enums.CandleDirection;
import com.coinbot.client.upbit.param.MinuteCandleParam;
import com.coinbot.domain.upbit.strategy.Strategy;
import com.coinbot.domain.upbit.trading.TradingInfo;
import com.coinbot.domain.upbit.trading.calculator.VolumePowerCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;

/**
 * 5분봉 3틱 전략
 */
@Component
@RequiredArgsConstructor
public class MyStrategy implements Strategy {

    private final UpbitClient upbit;
    private final VolumePowerCalculator volumePowerCalculator;
    private static final BigDecimal buyPercentage = new BigDecimal("0.2"); // 진입 시 현재 시드의 퍼센테이지 만큼 매수

    @Override
    public void execute() {
        String targetMarket = findTarget(); // 매수 타이밍인 종목 검색
        if (hasText(targetMarket)) {  // 매수하기에 적합한 종목이 있다면
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
            List<Candle> minuteCandles5 =
                        upbit.getCandles(MinuteCandleParam.builder()
                            .unit(5)         // 캔들 단위
                            .market(market)  // 캔들 종목
                            .count(20)       // 가져올 캔들의 수
                            .build());
            
            // 가져온 캔들에서의 고점
            double maxPrice = minuteCandles5.stream()
                    .mapToDouble(candle -> candle.getTradePrice().doubleValue())
                    .max()
                    .orElseThrow(() -> new IllegalArgumentException("캔들에서 가격 정보가 없습니다."));

            // 가져온 캔들에서의 저점
            double minPrice = minuteCandles5.stream()
                    .mapToDouble(candle -> candle.getTradePrice().doubleValue())
                    .min()
                    .orElseThrow(() -> new IllegalArgumentException("캔들에서 가격 정보가 없습니다."));

            // 변동 폭
            double changeLength = maxPrice - minPrice;

            // 현재가가 하한가보다 낮다면
            if (ticker.getTradePrice().doubleValue() < minPrice) {

            }
            
            // 현재 캔들 조회
            int size = minuteCandles5.size();
            for (int idx = 0; idx < size-1; idx++) {    // idx가 앞일 수록 최신캔들
                Candle candle = minuteCandles5.get(idx);
                // 캔들 길이
                BigDecimal length = candle.length();
                CandleDirection direction = candle.direction(minuteCandles5.get(idx+1)); // 캔들 방향
                System.out.println("direction = " + direction);
            }

            String coinName = TradingInfo.getCoinName(market);
        }

        return top5Volume24h.get(0).getMarket();
    }
}
