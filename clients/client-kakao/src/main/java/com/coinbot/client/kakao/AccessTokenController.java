package com.coinbot.client.kakao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AccessTokenController {

    private final KakaoClient kakaoClient;

    @GetMapping("/access-token")
    public void getAccessToken(@RequestParam("code") String code) {
        log.info("redirect url 매핑 성공 code = {}", code);
        kakaoClient.createAccessToken(code);
    }
}
