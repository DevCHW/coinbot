package com.coinbot.client.kakao;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

public class KakaoMessageApiConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            String token = KakaoClientConfig.accessToken;
            if (LocalDateTime.now().isAfter(KakaoClientConfig.accessTokenExpiredAt)) {
                token = KakaoClientConfig.refreshToken;
            }
            requestTemplate
                    .header(AUTHORIZATION, "Bearer " + token)
                    .header(CONTENT_TYPE, "application/x-www-form-urlencoded;charset=utf-8");
        };
    }

}
