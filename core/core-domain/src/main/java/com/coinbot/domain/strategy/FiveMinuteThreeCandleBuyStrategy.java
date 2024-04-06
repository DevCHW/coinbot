package com.coinbot.domain.strategy;

import com.coinbot.client.UpbitClient;
import com.coinbot.client.model.Candle;
import com.coinbot.client.model.Ticker;
import com.coinbot.client.param.CandleParam;
import com.coinbot.domain.trading.TradingInfo;
import com.coinbot.domain.trading.TradingService;
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
public class FiveMinuteThreeCandleBuyStrategy implements Strategy {

    private final UpbitClient upbit;
    private final TradingService tradingService;
    private static final BigDecimal buyPercentage = new BigDecimal("0.2"); // 진입 시 현재 시드의 퍼센테이지 만큼 매수

    @Override
    public void execute() {
        String target = findTarget(); // 매수 타이밍인 종목 검색
        if (hasText(target)) {
            BigDecimal price = TradingInfo.seedMoney.multiply(buyPercentage);
            System.out.println("매수 타이밍 조건을 만족하여 매수 주문을 실행합니다.");
            System.out.println("현재 시드머니 = " + TradingInfo.seedMoney + "진입할 금액 = " + price);
            System.out.println("종목 = " + target);
//            tradingService.buy(target, price); // 매수 주문
            // 스탑로스 주문
        }
    }

    // 5분봉 3틱 매수타이밍 종목 선정
    private String findTarget() {
        // 모든 Ticker를 조회한다.
        List<Ticker> tickers = upbit.getTickers(TradingInfo.marketList);

        // 24시간 기준 거래대금 Top 5 종목을 필터링한다.
        List<Ticker> top5Volume24h = tickers.stream()
                .sorted(Comparator.comparing(Ticker::getAccTradePrice24h).reversed())
                .limit(5)
                .toList();
        // 체결 강도는 계산을 넣을 지 말지?

        // 위 단계를 통과한 종목들의 5분봉 3캔들을 가져온다.

        // 해당 5종목의 변동성이 어느정도인지 체크한다.

        // 변동성에 따라서 매도 가격, 손절 가격 등을 계산한다.
        String market = top5Volume24h.get(0).getMarket(); // 종목
        CandleParam candleParam = CandleParam.builder()
                .unit(5)        // 캔들 단위
                .market(market) // 캔들 종목
                .count(3)       // 가져올 캔들의 수
                .build();
        List<Candle> minuteCandles = upbit.getCandles(candleParam);
        for (Candle minuteCandle : minuteCandles) {
            System.out.println("minuteCandle = " + minuteCandle);
        }

        // 5분봉 3캔들 적합성 검사
        return top5Volume24h.get(0).getMarket();
    }
}
