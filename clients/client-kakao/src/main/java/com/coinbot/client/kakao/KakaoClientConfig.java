package com.coinbot.client.kakao;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Configuration
@EnableFeignClients
public class KakaoClientConfig {

    public static String accessToken;
    public static String refreshToken;
    public static LocalDateTime accessTokenExpiredAt;
    public static LocalDateTime refreshTokenExpiredAt;

    public KakaoClientConfig(Environment env) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        String accessTokenExpiredAtStr = env.getProperty("kakao.access.token.expiredAt");
        String refreshTokenExpiredAtStr = env.getProperty("kakao.refresh.token.expiredAt");

        if (Objects.nonNull(accessTokenExpiredAtStr)) {
            accessTokenExpiredAt = LocalDateTime.parse(accessTokenExpiredAtStr, formatter);
        }
        if (Objects.nonNull(refreshTokenExpiredAtStr)) {
            refreshTokenExpiredAt = LocalDateTime.parse(refreshTokenExpiredAtStr, formatter);
        }
        accessToken = env.getProperty("kakao.access.token");
        refreshToken = env.getProperty("kakao.refresh.token");
    }

}
