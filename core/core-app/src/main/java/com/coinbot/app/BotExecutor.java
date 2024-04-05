package com.coinbot.app;

import com.coinbot.client.UpbitClient;
import com.coinbot.client.model.Account;
import com.coinbot.client.model.Market;
import com.coinbot.domain.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BotExecutor {

    private final OrderService orderService;
    private final UpbitClient upbitClient;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        List<Account> account = upbitClient.getAccount();
        System.out.println("account = " + account);

        List<Market> markets = upbitClient.getMarkets();
        for (Market market : markets) {
            System.out.println("market = " + market);
        }
    }

}
