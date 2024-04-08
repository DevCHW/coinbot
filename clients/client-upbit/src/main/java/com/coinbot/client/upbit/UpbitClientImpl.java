package com.coinbot.client.upbit;

import com.coinbot.client.upbit.model.*;
import com.coinbot.client.upbit.param.MinuteCandleParam;
import com.coinbot.client.upbit.param.OrderParam;
import com.coinbot.client.upbit.param.TickParam;
import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class UpbitClientImpl implements UpbitClient {

    private final UpbitApi upbitApi;
    private final Environment env;

    // 계좌 조회
    @Override
    public List<Account> getAccount() {
        return upbitApi.getAccount();
    }

    // 주문
    @Override
    public Order order(OrderParam param) {
        return upbitApi.order(param);
    }

    // 코인 전체 목록 조회
    @Override
    public List<Market> getMarkets() {
        Boolean isDetails = true;
        return upbitApi.getMarkets(isDetails);
    }

    // 분봉 캔들 목록 조회
    @Override
    public List<Candle> getCandles(MinuteCandleParam param) {
        return upbitApi.getMinuteCandles(param.getUnit(), param.getMarket(), param.getCount(), param.getTo());
    }

    // 분봉 캔들 단건 조회
    @Override
    public Candle getCandle(MinuteCandleParam param) {
        return upbitApi.getMinuteCandles(param.getUnit(), param.getMarket(), 1, param.getTo()).get(0);
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

    // 체결 목록 조회
    @Override
    public List<Tick> getTicks(TickParam param) {
        return upbitApi.getTicks(param.getMarket(), param.getCount(), param.getTo(), param.getDaysAgo(), param.getCursor());
    }
    
    // 마켓에 따른 주문 가능 정보 조회
    @Override
    public OrderChance getOrderChance(String market) {
        return upbitApi.getOrderChance(market);
    }

    // 주문 정보 조회
    @Override
    public Order getOrder(String uuid) {
        return upbitApi.getOrder(uuid);
    }


    // test
    @Override
    public void testOrder(OrderParam orderParam) {
        try {
            String accessKey = env.getProperty("upbit.access.key");
            String secretKey = env.getProperty("upbit.secret.key");
            String serverUrl = "https://api.upbit.com";

            HashMap<String, String> params = new HashMap<>();
            params.put("market", orderParam.getMarket());
            params.put("side", orderParam.getSide());
            params.put("volume", String.valueOf(orderParam.getVolume()));
            params.put("ord_type", orderParam.getOrdType());

            ArrayList<String> queryElements = new ArrayList<>();
            for(Map.Entry<String, String> entity : params.entrySet()) {
                queryElements.add(entity.getKey() + "=" + entity.getValue());
            }

            String queryString = String.join("&", queryElements.toArray(new String[0]));
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(queryString.getBytes(StandardCharsets.UTF_8));

            String queryHash = String.format("%0128x", new BigInteger(1, md.digest()));

            Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
            Map<String, Object> claims = new ConcurrentHashMap<>();
            claims.put("access_key", accessKey);
            claims.put("nonce", UUID.randomUUID().toString());
            claims.put("query_hash", queryHash);
            claims.put("query_hash_alg", "SHA512");
            String jwtToken = Jwts.builder()
                    .claims(claims)
                    .signWith(key)
                    .compact();

            String authenticationToken = "Bearer " + jwtToken;

            HttpClient client = HttpClientBuilder.create().build();
            HttpPost request = new HttpPost(serverUrl + "/v1/orders");
            request.setHeader("Content-Type", "application/json");
            request.addHeader("Authorization", authenticationToken);
            request.setEntity(new StringEntity(new Gson().toJson(params)));
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
