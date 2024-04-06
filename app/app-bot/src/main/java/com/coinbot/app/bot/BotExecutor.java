package com.coinbot.app.bot;

import com.coinbot.domain.strategy.Strategy;
import com.coinbot.domain.strategy.Test;
import com.coinbot.domain.trading.TradingBotInitializer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BotExecutor {

    private final TradingBotInitializer initializer;
    private final Strategy strategy;
    private final Test test;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        initializer.initialize();
    }

    @Scheduled(fixedDelay = 3000, initialDelay = 3000)
    public void trade() {
//        strategy.execute();   // 매매 전략 실행
        test.getTicksTest();
    }

}
