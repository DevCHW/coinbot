package com.coinbot.client;

import com.coinbot.client.model.Account;

public interface UpbitClient {

    /**
     * 계좌 조회
     */
    Account getAccount();

}
