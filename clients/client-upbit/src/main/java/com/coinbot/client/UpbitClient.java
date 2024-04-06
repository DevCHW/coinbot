package com.coinbot.client;

import com.coinbot.client.model.*;
import com.coinbot.client.param.BuyParam;
import com.coinbot.client.param.CandleParam;
import com.coinbot.client.param.SellParam;
import com.coinbot.client.param.TickParam;

import java.util.List;

public interface UpbitClient {

    /**
     * 계좌 조회
     */
    List<Account> getAccount();

    /**
     * 매수
     */
    Order buy(BuyParam param);

    /**
     * 매도
     */
    Order sell(SellParam param);

    /**
     * 코인 전체 목록 조회
     */
    List<Coin> getCoins();

    /**
     * 분봉 캔들 조회
     */
    List<Candle> getCandles(CandleParam param);

    /**
     * 현재가 정보 목록 조회
     */
    List<Ticker> getTickers(List<String> markets);


    /**
     * 현재가 정보 단건 조회
     */
    Ticker getTicker(String market);

    // 체결 목록 조회
    List<Tick> getTicks(TickParam param);

}
