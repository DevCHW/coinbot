package com.coinbot.client.kakao;

import com.coinbot.client.kakao.model.Message;
import com.coinbot.client.kakao.model.Token;
import com.coinbot.client.kakao.param.CreateTokenParam;
import com.coinbot.client.kakao.util.JsonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class KaKaoClientImpl implements KakaoClient {

    private final ObjectMapper objectMapper;
    private final KakaoAuthApi authApi;
    private final KakaoMessageApi messageApi;
    private final Environment env;

    public KaKaoClientImpl(ObjectMapper objectMapper, KakaoAuthApi authApi, KakaoMessageApi messageApi, Environment env) {
        this.objectMapper = objectMapper;
        this.authApi = authApi;
        this.messageApi = messageApi;
        this.env = env;
    }

    // 액세스 토큰 발급
    @Override
    public void createAccessToken() {
        String restApiKey = env.getProperty("kakao.rest-api.key");
        String code = env.getProperty("kakao.auth.code");
        String redirectUri = env.getProperty("kakao.redirect-uri");
        CreateTokenParam param = CreateTokenParam.builder()
                .grantType("authorization_code")
                .clientId(restApiKey)
                .redirectUri(redirectUri)
                .code(code)
                .build();
        Token token = authApi.createAccessToken(param.toString());
        KakaoClientConfig.refreshToken = token.getRefreshToken();
        KakaoClientConfig.accessToken = token.getAccessToken();
        KakaoClientConfig.accessTokenExpiredAt = token.accessTokenExpiredAt();
        KakaoClientConfig.refreshTokenExpiredAt = token.refreshTokenExpiredAt();
        System.out.println("token = " + token);
        System.out.println("token.accessTokenExpiredAt() = " + token.accessTokenExpiredAt());
        System.out.println("token.refreshTokenExpiredAt() = " + token.refreshTokenExpiredAt());
    }

    // 엑세스 토큰 API로 발급
    @Override
    public void createAccessToken(String code) {
        String restApiKey = env.getProperty("kakao.rest-api.key");
        String redirectUri = env.getProperty("kakao.redirect-uri");
        CreateTokenParam param = CreateTokenParam.builder()
                .grantType("authorization_code")
                .clientId(restApiKey)
                .redirectUri(redirectUri)
                .code(code)
                .build();
        Token token = authApi.createAccessToken(param.toString());
        KakaoClientConfig.refreshToken = token.getRefreshToken();
        KakaoClientConfig.accessToken = token.getAccessToken();
        KakaoClientConfig.accessTokenExpiredAt = token.accessTokenExpiredAt();
        KakaoClientConfig.refreshTokenExpiredAt = token.refreshTokenExpiredAt();
        System.out.println("token = " + token);
        System.out.println("token.accessTokenExpiredAt() = " + token.accessTokenExpiredAt());
        System.out.println("token.refreshTokenExpiredAt() = " + token.refreshTokenExpiredAt());
    }

    // 카톡 메세지 전송
    @Override
    public void postMessage(Message message) {
        String jsonString = "template_object=" + JsonUtil.objectToJson(objectMapper, message);
        String templateObject = "template_object=" + "{\"object_type\": \"text\", \"text\": \"텍스트 영역입니다. 최대 200자 표시 가능합니다.\", \"link\": {\"web_url\": \"https://developers.kakao.com\", \"mobile_web_url\": \"https://developers.kakao.com\"}, \"button_title\": \"바로 확인\"}";
        System.out.println("jsonString = " + jsonString);
        messageApi.postMessage(templateObject);
    }
}
