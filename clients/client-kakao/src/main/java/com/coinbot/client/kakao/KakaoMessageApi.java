package com.coinbot.client.kakao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "${kakao.message.api.value}", url = "${kakao.message.api.url}", configuration = KakaoMessageApiConfig.class)
public interface KakaoMessageApi {

    @PostMapping("/v2/api/talk/memo/default/send")
    KakaoResponse postMessage(@RequestBody String templateObject);
}
