package com.coinbot.domain.trading;

import com.coinbot.client.UpbitClient;
import com.coinbot.client.model.Account;
import com.coinbot.client.model.Market;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TradingInfoInitializer {

    private final UpbitClient upbit;

    // 초기화
    public void initialize() {
        // 자산 조회
        List<Account> accounts = upbit.getAccount();

        // 보유 자산 세팅
        Map<String, TradingInfo.Asset> assets = accounts.stream()
                .collect(Collectors.toMap(
                    Account::getCurrency, // KEY
                    account -> TradingInfo.Asset.builder()
                            .symbol(account.getCurrency())
                            .balance(account.getBalance())
                            .locked(account.getLocked())
                            .avgBuyPrice(account.getAvgBuyPrice())
                            .avgBuyPriceModified(account.getAvgBuyPriceModified())
                            .unitCurrency(account.getUnitCurrency())
                            .build()    // VALUE
                ));

        // 트레이딩 여부 세팅
        boolean trading = false;

        // 업비트 전체 마켓 이름 목록 세팅
        List<Market> markets = upbit.getMarkets();
        List<String> marketList = markets.stream()
                .map(Market::getMarket)
                .filter(market -> market.startsWith("KRW")) // KRW 지원 마켓 필터링
                .toList();

        // 트레이딩 정보 세팅
        TradingInfo.InitializeParam param = TradingInfo.InitializeParam.builder()
                .trading(trading)
                .assets(assets)
                .marketList(marketList)
                .build();
        TradingInfo.init(param);
    }
}
