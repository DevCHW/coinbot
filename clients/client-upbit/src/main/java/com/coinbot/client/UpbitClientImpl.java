package com.coinbot.client;

import com.coinbot.client.model.Account;
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
    public Account getAccount() {
        List<Account> account = upbitApi.getAccount();
        System.out.println("account = " + account);
        return null;
    }

    private String getAuthorization(Map<String, String> params) {
        return "Bearer " + tokenProvider.getToken(params);
    }

}
