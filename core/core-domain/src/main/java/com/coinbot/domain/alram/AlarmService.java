package com.coinbot.domain.alram;

import com.coinbot.client.kakao.KakaoClient;
import com.coinbot.client.kakao.model.Link;
import com.coinbot.client.kakao.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlarmService {

    private final KakaoClient kakao;

    public void init() {
//        kakao.createAccessToken();    // 토큰 발급
//        Message template = Message.builder()
//                .objectType("text")
//                .text("텍스트 영역입니다. 최대 200자 표시 가능합니다.")
//                .link(Link.builder()
//                        .webUrl("https://developers.kakao.com")
//                        .mobileUrl("https://developers.kakao.com")
//                        .build())
//                .buttonTitle("바로 확인")
//                .build();
//        kakao.postMessage(template);  // 카톡 전송
    }
}
