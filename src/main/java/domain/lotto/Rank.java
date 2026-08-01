package domain.lotto;

import java.util.Arrays;

public enum Rank {
    NONE(0, Money.ZERO),
    THREE(3, Money.from(5000)),
    FOUR(4, Money.from(50000)),
    FIVE(5, Money.from(1500000)),
    SECOND(5, Money.from(30000000)),
    SIX(6, Money.from(2000000000));

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
