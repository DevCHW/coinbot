package com.coinbot.client.kakao.param;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateTokenParam {

    private String grantType;
    private String clientId;
    private String redirectUri;
    private String code;

    @Builder
    public CreateTokenParam(String grantType, String clientId, String redirectUri, String code) {
        this.grantType = grantType;
        this.clientId = clientId;
        this.redirectUri = redirectUri;
        this.code = code;
    }

    // kakao는 Content-Type 을 application/x-www-form-urlencoded 로 받는다.
    // FeignClient는 기본이 JSON으로 변경하니 아래처럼 데이터를 변환 후 보내야 한다.
    @Override
    public String toString() {
        return  "code=" + code + '&' +
                "client_id=" + clientId + '&' +
                "redirect_uri=" + redirectUri + '&' +
                "grant_type=" + grantType;
    }

}
