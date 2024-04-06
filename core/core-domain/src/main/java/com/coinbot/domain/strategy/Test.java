package com.coinbot.domain.strategy;

import com.coinbot.client.UpbitClient;
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
        LocalDateTime to = LocalDateTime.now();
        Integer count = 200;
        TickParam param = TickParam.builder()
                .market(market)
                .to(to)
                .count(count)
                .build();
        List<Tick> ticks = upbit.getTicks(param);
        System.out.println("ticks = " + ticks);
    }

}
