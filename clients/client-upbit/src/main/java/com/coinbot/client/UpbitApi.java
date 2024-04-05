package com.coinbot.client;

import com.coinbot.client.model.Account;
import com.coinbot.client.model.Market;
import com.coinbot.client.model.Order;
import feign.Headers;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "${upbit.api.value}", url = "${upbit.api.url}")
interface UpbitApi {

    /**
     * 계좌 조회
     */
    @GetMapping("/v1/accounts")
    List<Account> getAccount();

    /**
     * 주문
     */
    @PostMapping("/v1/orders")
    Order order(@RequestHeader("Authorization") String authorization);


    /**
     * 마켓 목록 조회
     */
    @GetMapping("/v1/market/all")
    List<Market> getMarket(@RequestParam("isDetails") Boolean isDetails);
}
