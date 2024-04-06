package com.coinbot.client;

import com.coinbot.client.model.*;
import com.coinbot.client.param.BuyParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(value = "${upbit.api.value}", url = "${upbit.api.url}")
interface UpbitApi {

    /**
     * 계좌 목록 조회
     */
    @GetMapping("/v1/accounts")
    List<Account> getAccount();

    /**
     * 매수
     */
    @PostMapping(value = "/v1/orders")
    Order buy(@RequestBody BuyParam param);


    /**
     * 코인 목록 조회
     */
    @GetMapping("/v1/market/all")
    List<Coin> getCoins(@RequestParam("isDetails") Boolean isDetails);

    /**
     * 분봉 캔들 목록 조회
     */
    @GetMapping("/v1/candles/minutes/{unit}")
    List<Candle> getMinuteCandles(@PathVariable("unit") int unit,
                                  @RequestParam("market") String market,
                                  @RequestParam("count") Integer count,
                                  @RequestParam(value = "to", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime to);

    /**
     * 현재가 정보 조회
     */
    @GetMapping("/v1/ticker")
    List<Ticker> getTickers(@RequestParam("markets") MultiValueMap<String, String> markets);
}
