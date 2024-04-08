package com.coinbot.domain.upbit.trading;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TradingInfo {

    private static Map<String, Asset> assets;       // 업비트 보유 자산 목록
    private static List<String> marketList;         // 업비트 전체 마켓 이름 목록
    private static boolean trading;                 // 현재 트레이딩 진행 여부

    private TradingInfo() {

    }

    public static BigDecimal seedMoney() {
        Asset krwAsset = assets.get("KRW");
        if (Objects.isNull(krwAsset)) {
            return new BigDecimal("0");
        }
        return new BigDecimal(String.valueOf(krwAsset.getBalance().intValue()));
    }
    
    // 시드머니 수정
    public static void seedMoney(BigDecimal value) {
        Asset krwAsset = assets.get("KRW");
        krwAsset.balance(value);
    }

    public static boolean trading() {
        return trading;
    }

    public static void trading(boolean value) {
        trading = value;
    }

    public static List<String> marketList() {
        return marketList;
    }

    public static void marketList(List<String> value) {
        marketList = value;
    }

    public static Map<String, Asset> assets() {
        return assets;
    }

    public static BigDecimal quantity(String market) {
        String prefix = "KRW-";
        int idx = market.indexOf(prefix);
        String symbol = market.substring(idx + prefix.length());
        return assets.get(symbol).getBalance();
    }

    public static void init(InitializeParam param) {
        trading = param.isTrading();
        marketList = param.getMarketList();
        assets = param.getAssets();
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class InitializeParam {
        private boolean trading;                // 현재 트레이딩 진행 여부
        private BigDecimal seedMoney;           // 시드머니(KRW 기준)
        private List<String> marketList;        // 전체 마켓 이름 목록
        private Map<String, Asset> assets; // 마켓별 보유 수량

        @Builder
        private InitializeParam(boolean trading, BigDecimal seedMoney, List<String> marketList, Map<String, Asset> assets) {
            this.trading = trading;
            this.seedMoney = seedMoney;
            this.marketList = marketList;
            this.assets = assets;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Asset {
        private String symbol;               // 화폐를 의미하는 영문 대문자 코드 (코인이름 또는 화폐)
        private BigDecimal balance;          // 주문 가능 금액/수량
        private BigDecimal locked;           // 주문 중 묶여있는 금액/수량
        private BigDecimal avgBuyPrice;      // 매수평균가
        private Boolean avgBuyPriceModified; // 매수평균가 수정여부
        private String unitCurrency;         // 평단가 기준 화폐

        @Builder
        public Asset(String symbol, BigDecimal balance, BigDecimal locked, BigDecimal avgBuyPrice, Boolean avgBuyPriceModified, String unitCurrency) {
            this.symbol = symbol;
            this.balance = balance;
            this.locked = locked;
            this.avgBuyPrice = avgBuyPrice;
            this.avgBuyPriceModified = avgBuyPriceModified;
            this.unitCurrency = unitCurrency;
        }

        public void balance(BigDecimal balance) {
            this.balance = balance;
        }
    }

}
