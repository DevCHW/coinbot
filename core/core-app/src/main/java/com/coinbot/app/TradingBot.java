package com.coinbot.app;

import com.coinbot.annotation.BotExecutePlanner;
import com.coinbot.domain.TradingService;
import com.coinbot.domain.strategy.FiveMinuteThreeCandleBuyStrategy;
import com.coinbot.domain.strategy.Test;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@BotExecutePlanner
@RequiredArgsConstructor
public class TradingBot {

    private final TradingService tradingService;
    private final Test test;
    private final FiveMinuteThreeCandleBuyStrategy strategy;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        tradingService.initialize();
        // TODO : 테스트 중.. 지우기
        strategy.execute();
    }

}
