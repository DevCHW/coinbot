package com.coinbot.client;

import com.coinbot.client.model.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "${upbit.api.value}", url = "${upbit.api.url}")
interface UpbitApi {

    /**
     * 계좌 조회
     */
    @GetMapping(value = "/v1/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Account> getAccount();

}
