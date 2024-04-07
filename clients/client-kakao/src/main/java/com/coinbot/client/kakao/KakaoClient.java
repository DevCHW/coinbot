package com.coinbot.client.kakao;

import com.coinbot.client.kakao.model.Message;

public interface KakaoClient {

    void createAccessToken();

    void postMessage(Message message);

    void createAccessToken(String code);
}
