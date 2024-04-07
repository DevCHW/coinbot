package com.coinbot.client.kakao;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "${kakao.message.api.value}", url = "${kakao.message.api.url}")
public interface KakaoMessageApi {

}
