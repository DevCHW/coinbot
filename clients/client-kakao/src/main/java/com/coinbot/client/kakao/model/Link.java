package com.coinbot.client.kakao.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Link {
    private String webUrl;
    private String mobileUrl;

    @Builder
    public Link(String webUrl, String mobileUrl) {
        this.webUrl = webUrl;
        this.mobileUrl = mobileUrl;
    }

    @Override
    public String toString() {
        return  "webUrl=" + webUrl + '&' +
                "mobileUrl=" + mobileUrl;
    }
}
