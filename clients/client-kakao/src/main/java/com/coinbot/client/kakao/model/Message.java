package com.coinbot.client.kakao.model;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Message {
    private String objectType;
    private String text;
    private Link link;
    private String buttonTitle;

    @Builder
    public Message(String objectType, String text, Link link, String buttonTitle) {
        this.objectType = objectType;
        this.text = text;
        this.link = link;
        this.buttonTitle = buttonTitle;
    }

    @Override
    public String toString() {
        return  "objectType=" + objectType + '&' +
                "text=" + text + '&' +
                "link=" + link + '&' +
                "buttonTitle=" + buttonTitle;
    }

}
