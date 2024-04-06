package com.coinbot.domain.strategy;

import com.coinbot.client.UpbitClient;
import com.coinbot.client.model.Candle;
import com.coinbot.client.model.Ticker;
import com.coinbot.client.param.CandleParam;
import com.coinbot.domain.TradingInfo;
import com.coinbot.domain.TradingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * 5분봉 3틱 매수 전략
 */
@Component
@RequiredArgsConstructor
public class FiveMinuteThreeCandleBuyStrategy {

    private final UpbitClient upbit;
    private final TradingService tradingService;

    // 5분봉 3틱 매수 전략 실행
    public void execute() {
        String target = findTarget(); // 매수 타이밍인 종목 검색
        if (Objects.nonNull(target)) {
            tradingService.buy(target); // 매수
        }
    }

    // 5분봉 3틱 매수타이밍 종목 선정
    private String findTarget() {
        // 모든 Ticker를 조회한다.
        List<Ticker> tickers = upbit.getTickers(TradingInfo.marketList);

        // 24시간 기준 거래대금 Top 5 종목 선정.
        List<Ticker> top5Volume24h = tickers.stream()
                .sorted(Comparator.comparing(Ticker::getAccTradePrice24h).reversed())
                .limit(5)
                .toList();

        // 해당 5종목의 변동성이 많은 지 체크한다.


        // 체결 강도는 어떠한지?


        // 위 단계를 통과한 종목들의 5분봉 3캔들을 가져온다.
        int unit = 5;  // 캔들 단위
        int count = 3; // 가져 올 캔들의 개수
        String market = top5Volume24h.get(0).getMarket(); // 종목
        CandleParam candleParam = CandleParam.builder()
                .market(market)
                .count(count)
                .build();
        List<Candle> minuteCandles = upbit.getCandles(unit, candleParam);
        for (Candle minuteCandle : minuteCandles) {
            System.out.println("minuteCandle = " + minuteCandle);
        }

        // 5분봉 3캔들 적합성 검사
        return null;
    }
}
