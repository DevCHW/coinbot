package com.coinbot.web.api;

import com.coinbot.domain.TradingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final TradingService orderService;

}
