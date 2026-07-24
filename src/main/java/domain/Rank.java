package domain;

import java.util.Arrays;

public enum Rank {
    NONE(0, 0),
    THREE(3, 5000),
    FOUR(4, 50000),
    FIVE(5, 1500000),
    SIX(6, 2000000000);

    private final int matchCount;
    private final int price;

    Rank(int matchCount, int price) {
        this.matchCount = matchCount;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public static Rank findByMatchCount(int matchCount) {
        return Arrays.stream(values())
                .filter(rank -> rank.matches(matchCount))
                .findFirst()
                .orElse(NONE);
    }
    private boolean matches(int matchCount) {
        return this.matchCount == matchCount;
    }
}
