package com.coinbot.domain.trading;

import com.coinbot.client.UpbitClient;
import com.coinbot.client.model.Account;
import com.coinbot.client.model.Coin;
import com.coinbot.domain.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.coinbot.domain.trading.TradingInfo.*;

@Service
@RequiredArgsConstructor
public class TradingBotInitializer {

    private final UpbitClient upbit;

    // 초기화
    public void initialize() {
        // 시드머니 세팅
        List<Account> accounts = upbit.getAccount();
        Account account = accounts.stream()
                .filter(myAccount -> myAccount.getCurrency().equals("KRW"))
                .findFirst()
                .orElseThrow(() -> new BaseException("No SeedMoney."));
        seedMoney = account.getBalance();

        // 트레이딩 진행 여부 세팅
        if (accounts.size() > 1) {
            isTrading = true;
        }

        // 업비트 전체 마켓 이름 목록 세팅
        List<Coin> coins = upbit.getCoins();
        marketList = coins.stream()
                .map(Coin::getMarket)
                .filter(market -> market.startsWith("KRW")) // KRW 지원 마켓 필터링
                .toList();

    }
}
