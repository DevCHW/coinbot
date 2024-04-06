package com.coinbot.domain.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class StrategyExecutor {

    private final Map<String, Strategy> strategy;

    public void execute(StrategyType strategyType) {
        Strategy findStrategy = strategy.get(strategyType.getName());
        findStrategy.execute();
    }

}
