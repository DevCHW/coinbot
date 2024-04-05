package com.coinbot.client;

import com.coinbot.client.model.Account;
import com.coinbot.client.model.CreateOrder;
import com.coinbot.client.model.Market;
import com.coinbot.client.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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

    // 주문 하기
    @Override
    public Order order(CreateOrder orderParam) {
        String authorization = getAuthorization(orderParam.toMap());
        return upbitApi.order(authorization);
    }

    // 마켓 목록 조회
    @Override
    public List<Market> getMarkets() {
        Boolean isDetails = true;
        return upbitApi.getMarket(isDetails);
    }

    // 쿼리파라미터 Authorization 조회
    private String getAuthorization(Map<String, Object> params) {
        return "Bearer " + tokenProvider.getToken(params);
    }


}
