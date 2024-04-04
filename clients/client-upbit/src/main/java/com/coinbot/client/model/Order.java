package com.coinbot.client.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Order {
    private String uuid;            // 주문의 고유 아이디
    private String side;            // 주문 종류
    private String ordType;         // 주문 방식
    private Long price;             // 주문 당시 화폐 가격
    private String state;           // 주문 상태
    private String market;          // 마켓의 유일키
    private String createdAt;       // 주문 생성 시간
    private Long volume;            // 사용자가 입력한 주문 양
    private Long remainingVolume;   // 체결 후 남은 주문 양
    private Long reservedFee;       // 수수료로 예약된 비용
    private Long remainingFee;      // 남은 수수료
    private Long paidFee;           // 사용된 수수료
    private Long locked;            // 거래에 사용중인 비용
    private Long executedVolume;    // 체결된 양
    private Integer tradesCount;    // 해당 주문에 걸린 체결 수
    private String timeInForce;     // IOC, FOK 설정
}
