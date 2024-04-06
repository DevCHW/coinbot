package com.coinbot.client;

import com.coinbot.client.model.*;
import com.coinbot.client.param.BuyParam;
import com.coinbot.client.param.CandleParam;
import com.coinbot.client.param.SellParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UpbitClientImpl implements UpbitClient {

    private final UpbitApi upbitApi;
    private final UpbitTokenProvider tokenProvider;

    // 계좌 조회
    @Override
    public List<Account> getAccount() {
        return upbitApi.getAccount();
    }

    // 매수
    @Override
    public Order buy(BuyParam param) {
        return upbitApi.buy(param);
    }

    // 매수
    @Override
    public Order sell(SellParam param) {
//        return upbitApi.order(param);
        return null;
    }

    // 코인 전체 목록 조회
    @Override
    public List<Coin> getCoins() {
        Boolean isDetails = true;
        return upbitApi.getCoins(isDetails);
    }

    // 분봉 캔들 조회
    @Override
    public List<Candle> getCandles(int unit, CandleParam param) {
        return upbitApi.getMinuteCandles(unit, param.getMarket(), param.getCount(), param.getTo());
    }
    
    // 현재가 정보 목록 조회
    @Override
    public List<Ticker> getTickers(List<String> markets) {
        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        for (String market : markets) {
            param.add("markets", market);
        }
        return upbitApi.getTickers(param);
    }

    // 현재가 정보 단건 조회
    @Override
    public Ticker getTicker(String market) {
        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        param.add("markets", market);
        List<Ticker> tickers = upbitApi.getTickers(param);
        return tickers.get(0);
    }

}
