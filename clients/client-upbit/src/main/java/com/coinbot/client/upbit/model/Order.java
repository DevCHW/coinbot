package com.coinbot.client.upbit.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class Order {
    private String uuid;                    // 주문의 고유 아이디
    private String side;                    // 주문 종류
    private String ordType;                 // 주문 방식
    private BigDecimal price;               // 주문 당시 화폐 가격
    private String state;                   // 주문 상태
    private String market;                  // 마켓의 유일키
    private String createdAt;               // 주문 생성 시간
    private Long volume;                    // 사용자가 입력한 주문 양
    private Long remainingVolume;           // 체결 후 남은 주문 양
    private BigDecimal reservedFee;         // 수수료로 예약된 비용
    private BigDecimal remainingFee;        // 남은 수수료
    private BigDecimal paidFee;             // 사용된 수수료
    private BigDecimal locked;              // 거래에 사용중인 비용
    private Long executedVolume;            // 체결된 양
    private Integer tradesCount;            // 해당 주문에 걸린 체결 수
    private String timeInForce;             // IOC, FOK 설정
    private List<Trade> trades;             // 체결 목록

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    public static class Trade {
        private String market;              // 마켓의 유일 키
        private String uuid;                // 체결의 고유 아이디
        private BigDecimal price;           // 체결 가격
        private BigDecimal volume;          // 체결 양
        private BigDecimal funds;           // 체결된 총 가격
        private String side;                // 체결 종류
        private LocalDateTime createdAt;    // 체결 시각
    }

}
