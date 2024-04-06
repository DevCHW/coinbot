package com.coinbot.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class TradingInfo {
    public static boolean isTrading;            // 현재 트레이딩 진행 여부
    public static Double seedMoney;             // 시드머니(KRW 기준)
    public static List<String> marketList;      //전체 마켓 이름 목록


}
