package com.coinbot.client.kakao.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class Token {
    private String accessToken;         // 애플리케이션이 Google API 요청을 승인하기 위해 보내는 토큰
    private Long expiresIn;             // 액세스 토큰의 남은 수명
    private String refreshToken;        // 새 액세스 토큰을 얻는 데 사용할 수 있는 토큰
    private String scope;
    private String tokenType;           // 반환된 토큰 유형(Bearer 고정)
    private String idToken;
    private Long refreshTokenExpiresIn; // 리프레쉬 토큰 남은 수명

    public LocalDateTime accessTokenExpiredAt() {
        return LocalDateTime.now().plusSeconds(expiresIn);
    }

    public LocalDateTime refreshTokenExpiredAt() {
        return LocalDateTime.now().plusSeconds(refreshTokenExpiresIn);
    }
}
