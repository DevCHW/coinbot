package com.coinbot.app;

import com.coinbot.annotation.BotExecutor;
import com.coinbot.domain.strategy.Strategy;
import com.coinbot.domain.strategy.Test;
import com.coinbot.domain.trading.TradingService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@BotExecutor
@RequiredArgsConstructor
public class TradingBotExcutor {

    private final TradingService tradingService;
    private final Test test;
    private final Strategy strategy;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        tradingService.initialize();
        strategy.execute();
        test.getTicksTest();
    }

}
