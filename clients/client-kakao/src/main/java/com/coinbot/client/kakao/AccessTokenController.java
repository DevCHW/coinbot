package com.coinbot.client.kakao;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class AccessTokenController {

    @GetMapping("/access-token")
    public void getAccessToken(@RequestParam String code) {
        log.info("redirect url 매핑 성공 code = {}", code);
    }
}
