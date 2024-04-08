package com.coinbot.app.bot;

import com.coinbot.domain.upbit.alram.AlarmService;
import com.coinbot.domain.upbit.trading.TradingService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BotInitializer {

    private final TradingService tradingService;
    private final AlarmService alarmService;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        tradingService.init();  // 트레이딩 정보 초기화
        alarmService.init();    // 알림 정보 초기화
    }
}
