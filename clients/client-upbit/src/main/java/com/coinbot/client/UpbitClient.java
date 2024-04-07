package com.coinbot.client;

import com.coinbot.client.model.*;
import com.coinbot.client.param.OrderParam;
import com.coinbot.client.param.MinuteCandleParam;
import com.coinbot.client.param.TickParam;
import org.apache.http.ParseException;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UpbitClient {

    /**
     * 계좌 조회
     */
    List<Account> getAccount();

    /**
     * 주문
     */
    Order order(OrderParam param);

    /**
     * 코인 전체 목록 조회
     */
    List<Market> getMarkets();

    /**
     * 분봉 캔들 조회
     */
    List<Candle> getCandles(MinuteCandleParam param);

    /**
     * 현재가 정보 목록 조회
     */
    List<Ticker> getTickers(List<String> markets);


    /**
     * 현재가 정보 단건 조회
     */
    Ticker getTicker(String market);

    /**
     * 체결 목록 조회
     */
    List<Tick> getTicks(TickParam param);

    /**
     * 주문 정보 조회
     */
    Order getOrder(String uuid);

    /**
     * 마켓에 따른 주문 가능 정보 조회
     */
    OrderChance getOrderChance(String market);

    /**
     * TODO : 테스트 지우기
     */
    void testOrder(OrderParam orderParam);
}
