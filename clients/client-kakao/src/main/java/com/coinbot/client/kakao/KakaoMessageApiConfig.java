package com.coinbot.client.kakao;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

public class KakaoMessageApiConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate
                    .header(AUTHORIZATION, "Bearer " + KaKaoClientImpl.accessToken)
                    .header(CONTENT_TYPE, "application/x-www-form-urlencoded;charset=utf-8");
        };
    }

}
