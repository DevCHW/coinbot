package com.coinbot.domain.strategy.implement;

import com.coinbot.client.upbit.UpbitClient;
import com.coinbot.client.upbit.model.Candle;
import com.coinbot.client.upbit.model.Ticker;
import com.coinbot.client.upbit.model.enums.CandleDirection;
import com.coinbot.client.upbit.param.MinuteCandleParam;
import com.coinbot.domain.strategy.Strategy;
import com.coinbot.domain.trading.TradingInfo;
import com.coinbot.domain.trading.calculator.VolumePowerCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;

import static org.springframework.util.StringUtils.*;

/**
 * 5분봉 3틱 매수 전략
 */
@Component
@RequiredArgsConstructor
public class FiveMinuteThreeCandleTradingStrategy implements Strategy {

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
            BigDecimal changePrice = ticker.getSignedChangePrice();
            System.out.println("changePrice = " + changePrice);

            String market = ticker.getMarket();
            String symbol = market.replace("KRW-", "");


            List<Candle> minuteCandles5 =
                        upbit.getCandles(MinuteCandleParam.builder()
                            .unit(5)         // 캔들 단위
                            .market(market)  // 캔들 종목
                            .count(20)       // 가져올 캔들의 수
                            .build());
            OptionalDouble maxPrice = minuteCandles5.stream()
                    .mapToDouble(candle -> candle.getTradePrice().doubleValue())
                    .max();

            OptionalDouble minPrice = minuteCandles5.stream()
                    .mapToDouble(candle -> candle.getTradePrice().doubleValue())
                    .min();

            System.out.println("고가 = " + maxPrice);
            System.out.println("저가 = " + maxPrice);


            int size = minuteCandles5.size();
            System.out.println("=================== >> " + symbol + "코인 캔들 정보 출력(5분봉) << ===================");
            System.out.println("길이 단위 : KRW");
            int volumePower = volumePowerCalculator.volumePower(market);
            System.out.println("volumePower = " + volumePower);

            // 종목의 캔들 목록 검증
            for (int idx = 0; idx < size-1; idx++) {    // idx가 앞일 수록 최신캔들
                Candle candle = minuteCandles5.get(idx);
                CandleDirection direction = candle.direction(minuteCandles5.get(idx+1)); // 캔들 방향

                if (idx == 0 && direction == CandleDirection.DOWN) continue; // 현재 캔들이 음봉인 경우 패스

                System.out.println((idx+1) + "번째 캔들 정보 : 방향 = " + direction.getDescription() + "/ 캔들 길이 = " + candle.length() + "/ 시간 = " + candle.getCandleDateTimeKst());
                // 5분봉 3캔들이 음봉이며 매수하기에 적합한지 검사한다.
            }
            System.out.println();

        }

        return top5Volume24h.get(0).getMarket();
    }
}
