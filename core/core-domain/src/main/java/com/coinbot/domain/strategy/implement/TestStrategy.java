package com.coinbot.domain.strategy.implement;

import com.coinbot.domain.strategy.Strategy;
import com.coinbot.domain.trading.TradingInfo;
import com.coinbot.domain.trading.TradingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 테스트 클래스
 */
@Component
@RequiredArgsConstructor
public class TestStrategy implements Strategy {

    private final TradingService tradingService;

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

        String market = "KRW-BCH";
        BigDecimal seed = TradingInfo.seedMoney();
        BigDecimal price = seed.subtract(seed.multiply(new BigDecimal("0.05")));

//        OrderChance orderChance = upbit.getOrderChance(market);

        // BCH 전량 매수
//        tradingService.buy(market, price.setScale(0, RoundingMode.DOWN));

        tradingService.tradingInfoReload();

        // BCH 전량 매도
        tradingService.sellTmp(market, TradingInfo.quantity(market));

        tradingService.tradingInfoReload();
    }
}
