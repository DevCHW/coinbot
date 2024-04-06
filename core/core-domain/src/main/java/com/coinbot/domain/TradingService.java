package com.coinbot.domain;

import com.coinbot.client.UpbitClient;
import com.coinbot.client.model.Account;
import com.coinbot.client.model.Coin;
import com.coinbot.client.model.Order;
import com.coinbot.client.model.OrderType;
import com.coinbot.client.param.BuyParam;
import lombok.RequiredArgsConstructor;
import org.hibernate.dialect.identity.HSQLIdentityColumnSupport;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.coinbot.domain.TradingInfo.*;

@Service
@RequiredArgsConstructor
public class TradingService {

    private final UpbitClient upbit;

    private static final double buyPercentage = 0.2; // 매수 시 시드의 몇 퍼센트로 들어갈 것인지?

    // 트레이딩 정보 초기화
    public void initialize() {
        // 시드머니 세팅
        List<Account> accounts = upbit.getAccount();
        Account account = accounts.stream()
                .filter(myAccount -> myAccount.getCurrency().equals("KRW"))
                .findFirst()
                .orElseThrow(() -> new BaseException("No SeedMoney."));
        seedMoney = account.getBalance();
        for (Account account1 : accounts) {
            System.out.println("account1 = " + account1);
        }

        // 트레이딩 진행 여부 세팅
        if (accounts.size() > 1) {
            isTrading = true;
        }

        // 전체 마켓 이름 목록 세팅
        List<Coin> coins = upbit.getCoins();
        marketList = coins.stream()
                .map(Coin::getMarket)
                .filter(market -> market.startsWith("KRW")) // KRW 지원 마켓 필터링
                .toList();
    }
    
    // 매수
    public void buy(String market) {
        // 시드머니에서 설정한 퍼센테이지 만큼 진입량 설정.
        Long price = (long) (TradingInfo.seedMoney * 0.2);
        BuyParam param = BuyParam.builder()
                .market(market)
                .ordType(OrderType.PRICE)   //시장가 매수
                .price(price)
                .build();
        Order order = upbit.buy(param);
        System.out.println("order = " + order); // 주문 결과 출력
    }

    // 매도

}
