package com.coinbot.domain.strategy;

import com.coinbot.client.UpbitClient;
import com.coinbot.client.model.Side;
import com.coinbot.client.model.Tick;
import com.coinbot.client.param.TickParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 테스트 클래스
 */
@Component
@RequiredArgsConstructor
public class Test {

    private final UpbitClient upbit;

    public void getTicksTest() {
        String market = "KRW-BTC";
        TickParam param = TickParam.builder()
                .market(market)
                .count(200)
                .build();
        List<Tick> ticks = upbit.getTicks(param);
        System.out.println("ticks = " + ticks);
        System.out.println("ticks.size() = " + ticks.size());

        // 최근 200개의 체결 강도 계산
        
        // 매수 체결량
        List<Tick> bidTicks = ticks.stream()
                .filter(tick -> tick.getSide() == Side.BID)
                .toList();

        // 매도 체결량
        List<Tick> askTicks = ticks.stream()
                .filter(tick -> tick.getSide() == Side.ASK)
                .toList();

        // 체결강도 = 매수체결량/매도체결량 × 100%
        // 체결강도가 100보다 클 경우 매도보다 매수가 많은 것이며, 100보다 작을 경우는 매수보다 매도가 많다는 것을 의미

        int bidTickSize = bidTicks.size();
        System.out.println("bidTickSize = " + bidTickSize);
        int askTickSize = askTicks.size();
        System.out.println("askTickSize = " + askTickSize);
        int tickStrong = (bidTickSize * 100) / (askTickSize);
        System.out.println("tickStrong = " + tickStrong);
    }

}
