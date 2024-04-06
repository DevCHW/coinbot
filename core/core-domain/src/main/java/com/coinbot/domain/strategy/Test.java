package com.coinbot.domain.strategy;

import com.coinbot.client.UpbitClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 테스트 클래스
 */
@Component
@RequiredArgsConstructor
public class Test {

    private final UpbitClient upbit;

}
