package com.coinbot.client;

import com.coinbot.client.model.*;
import com.coinbot.client.param.OrderParam;
import feign.HeaderMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@FeignClient(value = "${upbit.api.value}", url = "${upbit.api.url}")
interface UpbitApi {

    /**
     * 계좌 목록 조회
     */
    @GetMapping("/v1/accounts")
    List<Account> getAccount();

    /**
     * 주문
     */
    @PostMapping(value = "/v1/orders")
    Order order(@RequestBody OrderParam param);

    /**
     * 코인 목록 조회
     */
    @GetMapping("/v1/market/all")
    List<Market> getMarkets(@RequestParam("isDetails") Boolean isDetails);

    /**
     * 분봉 캔들 목록 조회
     */
    @GetMapping("/v1/candles/minutes/{unit}")
    List<Candle> getMinuteCandles(@PathVariable("unit") int unit,
                                  @RequestParam("market") String market,
                                  @RequestParam("count") Integer count,
                                  @RequestParam(value = "to", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime to);

    /**
     * 일봉 캔들 목록 조회
     */
    @GetMapping("/v1/candles/days")
    List<Candle> getDayCandles(@RequestParam("market") String market,
                               @RequestParam("count") Integer count,
                               @RequestParam(value = "to", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime to,
                               @RequestParam(value = "convertingPriceUnit") String convertingPriceUnit);

    /**
     * 주봉 캔들 목록 조회
     */
    @GetMapping("/v1/candles/weeks")
    List<Candle> getWeekCandles(@RequestParam("market") String market,
                                @RequestParam("count") Integer count,
                                @RequestParam(value = "to", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime to,
                                @RequestParam(value = "convertingPriceUnit", required = false) String convertingPriceUnit);

    /**
     * 월봉 캔들 목록 조회
     */
    @GetMapping("/v1/candles/months")
    List<Candle> getMonthCandles(@RequestParam("market") String market,
                                 @RequestParam("count") Integer count,
                                 @RequestParam(value = "to", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime to);

    /**
     * 주문 가능 정보 조회
     */
    @GetMapping("/v1/orders/chance")
    OrderChance getOrderChance(@RequestParam("market") String market);

    /**
     * 개별 주문 조회
     */
    @GetMapping("/v1/order")
    Order getOrder(@RequestParam("uuid") String uuid);

    /**
     * 현재가 정보 조회
     */
    @GetMapping("/v1/ticker")
    List<Ticker> getTickers(@RequestParam("markets") MultiValueMap<String, String> markets);

    /**
     * 최근 체결 내역 조회
     */
    @GetMapping("/v1/trades/ticks")
    List<Tick> getTicks(@RequestParam("market") String market,
                        @RequestParam(value = "count", required = false) Integer count,
                        @RequestParam(value = "to", required = false) @DateTimeFormat(pattern = "HH:mm:ss") LocalDateTime to,
                        @RequestParam(value = "daysAgo", required = false) Integer daysAgo,
                        @RequestParam(value = "cursor", required = false) String cursor);

}

