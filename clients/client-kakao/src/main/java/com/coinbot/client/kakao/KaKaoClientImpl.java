package com.coinbot.client.kakao;

import com.coinbot.client.kakao.model.Token;
import com.coinbot.client.kakao.param.CreateTokenParam;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KaKaoClientImpl implements KakaoClient {

    private final KakaoAuthApi authApi;
    private final Environment env;

    @Override
    public String createAccessToken() {
        String restApiKey = env.getProperty("kakao.rest-api.key");
        System.out.println("restApiKey = " + restApiKey);
        String code = env.getProperty("kakao.auth.code");
        CreateTokenParam param = CreateTokenParam.builder()
                .grantType("authorization_code")
                .clientId(restApiKey)
                .redirectUri("http://localhost:8080/access-token")
                .code(code)
                .build();
        Token response = authApi.createAccessToken(param.toString());
        System.out.println("response = " + response);
        return null;
    }
}
