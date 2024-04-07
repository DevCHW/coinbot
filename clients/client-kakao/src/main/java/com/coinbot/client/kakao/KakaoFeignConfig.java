package com.coinbot.client.kakao;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

public class KakaoFeignConfig {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate
                    .header(CONTENT_TYPE, "application/x-www-form-urlencoded");
        };
    }
}
