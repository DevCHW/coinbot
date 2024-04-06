package com.coinbot.domain.strategy;

import com.coinbot.client.UpbitClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 테스트 클래스
 */
@Component
@RequiredArgsConstructor
public class TestStrategy implements Strategy {

    private final UpbitClient upbit;

    @Override
    public void execute() {
        System.out.println("테스트 전략 실행");
        String market = "KRW-BTC";
    }
}
