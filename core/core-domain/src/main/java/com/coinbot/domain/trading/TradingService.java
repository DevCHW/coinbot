package com.coinbot.domain.trading;

import com.coinbot.client.UpbitClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TradingService {

    private final UpbitClient upbit;




}
