package domain.rank;

import domain.money.Money;
import java.util.Arrays;

public enum Rank {

    FIRST(6, new Money("2000000000")),
    SECOND(5, new Money("1500000")),
    THIRD(4, new Money("50000")),
    FOURTH(3, new Money("5000")),
    NONE(0, Money.zero());

    private final int matchCount;
    private final Money prize;

    Rank(final int matchCount, final Money prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    public static Rank from(final int matchCount) {
        return Arrays.stream(values())
                .filter(rank -> rank.matchCount == matchCount)
                .findFirst()
                .orElse(NONE);
    }

    public int getMatchCount() {
        return matchCount;
    }

    public Money getPrize() {
        return prize;
    }
}
