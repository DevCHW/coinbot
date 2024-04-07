package com.coinbot.client.kakao;

import com.coinbot.client.kakao.model.Token;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "${kakao.auth.api.value}", url = "${kakao.auth.api.url}", configuration = KakaoFeignConfig.class)
public interface KakaoAuthApi {

    @PostMapping(value = "/oauth/token")
    Token createAccessToken(@RequestBody String param);
}
