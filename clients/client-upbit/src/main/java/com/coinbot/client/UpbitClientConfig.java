package com.coinbot.client;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@EnableFeignClients
@Configuration
@RequiredArgsConstructor
class UpbitClientConfig {

    private final String ENV_UPBIT_ACCESS_KEY = "upbit.access.key";
    private final String ENV_UPBIT_SECRET_KEY = "upbit.secret.key";
    private final Environment env;

    @Bean
    public UpbitTokenProvider tokenProvider() {
        String accessKey = env.getProperty(ENV_UPBIT_ACCESS_KEY);
        String secretKey = env.getProperty(ENV_UPBIT_SECRET_KEY);
        System.out.println("accessKey = " + accessKey);
        System.out.println("secretKey = " + secretKey);
        return new UpbitTokenProvider(accessKey, secretKey);
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        Map <String, Object> params = new ConcurrentHashMap<>();
        return requestTemplate ->
                requestTemplate
                .header("Authorization", tokenProvider().getToken(params))
                .header("Content-Type", "application/json");
    }

}
