package com.coinbot.client.upbit;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@EnableFeignClients
@RequiredArgsConstructor
class UpbitClientConfig {

    private final Environment env;

    @Bean
    public UpbitTokenProvider tokenProvider() {
        String accessKey = env.getProperty("upbit.access.key");
        String secretKey = env.getProperty("upbit.secret.key");
        return new UpbitTokenProvider(accessKey, secretKey);
    }
}
