package domain;

import java.util.Arrays;

public enum Rank {
    NONE(0, new Money(0)),
    THREE(3, new Money(5000)),
    FOUR(4, new Money(50000)),
    FIVE(5, new Money(1500000)),
    SECOND(5, new Money(30000000)),
    SIX(6, new Money(2000000000));

    private final int matchCount;
    private final Money price;

    Rank(int matchCount, Money price) {
        this.matchCount = matchCount;
        this.price = price;
    }

    public Money getPrice() {
        return price;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public static Rank findByMatchCount(int matchCount, boolean bonusMatched) {
        if (matchCount == 5 && bonusMatched) {
            return SECOND;
        }

        return Arrays.stream(values())
                .filter(rank -> rank.matches(matchCount))
                .findFirst()
                .orElse(NONE);
    }
    private boolean matches(int matchCount) {
        return this.matchCount == matchCount;
    }
}
