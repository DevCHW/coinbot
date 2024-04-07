package com.coinbot.domain.trading.calculator;

import com.coinbot.client.UpbitClient;
import com.coinbot.client.model.enums.Side;
import com.coinbot.client.model.Tick;
import com.coinbot.client.param.TickParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 체결 강도 계산기
 */
@Component
@RequiredArgsConstructor
public class VolumePowerCalculator {

    private final UpbitClient upbit;

    /**
     * 체결 강도 계산
     * 계산식 : 체결강도 = 매수체결량/매도체결량 × 100
     * 체결강도가 100보다 클 경우 매도보다 매수가 많은 것이며, 100보다 작을 경우는 매수보다 매도가 많다는 것을 의미
     */
    public int volumePower(String market) {
        // 최근 200개 체결 조회
        TickParam param = TickParam.builder()
                .market(market)
                .count(200)
                .build();
        List<Tick> ticks = upbit.getTicks(param);

        // 매수 체결량 필터링
        List<Tick> bidTicks = ticks.stream()
                .filter(tick -> tick.getSide() == Side.BID)
                .toList();

        // 매도 체결량 필터링
        List<Tick> askTicks = ticks.stream()
                .filter(tick -> tick.getSide() == Side.ASK)
                .toList();
        int bidTickSize = bidTicks.size();
        int askTickSize = askTicks.size();
        return (bidTickSize * 100) / (askTickSize); // 체결강도 = 매수체결량/매도체결량 × 100
    }
}
