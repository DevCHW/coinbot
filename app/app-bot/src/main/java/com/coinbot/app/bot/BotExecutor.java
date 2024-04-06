package com.coinbot.app.bot;

import com.coinbot.domain.strategy.Strategy;
import com.coinbot.domain.strategy.StrategyExecutor;
import com.coinbot.domain.strategy.StrategyType;
import com.coinbot.domain.trading.TradingInfoInitializer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
@EnableScheduling
public class BotExecutor {

    private final TradingInfoInitializer initializer;
    private final StrategyExecutor strategyExecutor;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        initializer.initialize();
    }

    @Scheduled(fixedDelay = 3000, initialDelay = 3000)
    public void trade() {
        strategyExecutor.execute(StrategyType.TEST);
    }

}
