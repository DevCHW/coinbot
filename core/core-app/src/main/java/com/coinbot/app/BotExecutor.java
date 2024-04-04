package com.coinbot.app;

import com.coinbot.client.UpbitClient;
import com.coinbot.domain.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class BotExecutor {

    private final OrderService orderService;
    private final UpbitClient upbitClient;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        upbitClient.getAccount();
    }

}
