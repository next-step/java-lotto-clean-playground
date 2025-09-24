package domain;

public enum LottoPrice {
    MATCH_3(5000),
    MATCH_4(50000),
    MATCH_5(1500000),
    MATCH_6(2000000000);

    private final int priceAmount;

    LottoPrice(int priceAmount) {
        this.priceAmount = priceAmount;
    }

    public int getPrizeAmount() {
        return priceAmount;
    }
}
