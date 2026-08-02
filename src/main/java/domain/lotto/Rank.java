package domain.lotto;

import java.util.Arrays;

public enum Rank {
    NONE(0, Money.ZERO, false),
    THREE(3, Money.from(5000), false),
    FOUR(4, Money.from(50000), false),
    FIVE(5, Money.from(1500000), false),
    SECOND(5, Money.from(30000000), true),
    SIX(6, Money.from(2000000000), false);

    private final int matchCount;
    private final Money price;
    private final boolean bonusRequired;

    Rank(int matchCount, Money price, boolean bonusRequired) {
        this.matchCount = matchCount;
        this.price = price;
        this.bonusRequired = bonusRequired;
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

    public Money calculatePrize(int count) {
        return price.multiply(count);
    }

    public Money getPrice() {
        return price;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isBonusRequired() {
        return bonusRequired;
    }
}
