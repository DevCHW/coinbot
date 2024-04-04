package com.coinbot.client;

import com.coinbot.client.model.Account;
import com.coinbot.client.model.CreateOrder;
import com.coinbot.client.model.Order;

import java.util.List;

public interface UpbitClient {

    /**
     * 계좌 조회
     */
    List<Account> getAccount();

    /**
     * 주문
     */
    Order order(CreateOrder orderParam);

}
