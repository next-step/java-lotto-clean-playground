package org.duckstudy.model.lotto;

public enum LottoMatch {

    MIN_MATCH(3, 5000, 3),
    MATCH_4(4, 50000, 4),
    MATCH_5(5, 1500000, 5),
    MATCH_5_WITH_BONUS(5, 30000000, -5),
    MAX_MATCH(6, 2000000000, 6);

    private final int matchCount;
    private final int price;
    private final int key;

    LottoMatch(int matchCount, int price, int key) {
        this.matchCount = matchCount;
        this.price = price;
        this.key = key;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrice() {
        return price;
    }

    public int getKey() {
        return key;
    }
}
