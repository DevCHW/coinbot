package com.coinbot.app.bot;

import com.coinbot.domain.strategy.StrategyExecutor;
import com.coinbot.domain.strategy.StrategyType;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@EnableScheduling
public class BotExecutor {

    private final StrategyExecutor strategyExecutor;

    @Scheduled(fixedDelay = 30000, initialDelay = 3000)
    public void trade() {
        strategyExecutor.execute(StrategyType.TEST);
    }

}
