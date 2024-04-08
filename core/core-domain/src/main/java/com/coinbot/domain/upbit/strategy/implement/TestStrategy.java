package com.coinbot.domain.upbit.strategy.implement;

import com.coinbot.client.kakao.KakaoClient;
import com.coinbot.domain.upbit.strategy.Strategy;
import com.coinbot.domain.upbit.trading.TradingInfo;
import com.coinbot.domain.upbit.trading.TradingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 테스트 클래스
 */
@Component
@RequiredArgsConstructor
public class TestStrategy implements Strategy {

    private final TradingService tradingService;
    private final KakaoClient kakaoClient;

    @Override
    public void execute() {
        System.out.println("테스트 실행");
        System.out.println("현재 시드머니 = " + TradingInfo.seedMoney());
        Map<String, TradingInfo.Asset> assets = TradingInfo.assets();
        System.out.println("=== > 보유 자산 목록 < =====");

        int count = 0;
        for (String symbol : assets.keySet()) {
            count++;
            TradingInfo.Asset asset = assets.get(symbol);
            System.out.println(count + ". 자산명 = " + asset.getSymbol());
            System.out.println(count + ". 수량 = " + asset.getBalance());
            System.out.println(count + ". 평단가 = " + asset.getAvgBuyPrice());
            System.out.println(count + ". 평단가 기준 화폐 = " + asset.getUnitCurrency());
            System.out.println();
        }

    }
}
