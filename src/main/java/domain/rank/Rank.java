package domain.rank;

import domain.money.Money;

public enum Rank {

    FIRST(6, false, new Money("2000000000")),
    SECOND(5, true, new Money("30000000")),
    THIRD(5, false, new Money("1500000")),
    FOURTH(4, false, new Money("50000")),
    FIFTH(3, false, new Money("5000")),
    NONE(0, false, Money.zero());

    private final int matchCount;
    private final boolean hasBonus;
    private final Money prize;

    Rank(final int matchCount, final boolean hasBonus, final Money prize) {
        this.matchCount = matchCount;
        this.hasBonus = hasBonus;
        this.prize = prize;
    }

    public static Rank from(final int matchCount, final boolean bonusMatch) {
        if (matchCount == 6) {
            return FIRST;
        }
        if (matchCount == 5 && bonusMatch) {
            return SECOND;
        }
        if (matchCount == 5) {
            return THIRD;
        }
        if (matchCount == 4) {
            return FOURTH;
        }
        if (matchCount == 3) {
            return FIFTH;
        }
        return NONE;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public Money getPrize() {
        return prize;
    }
}
