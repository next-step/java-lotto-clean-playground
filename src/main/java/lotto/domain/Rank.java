package lotto.domain;

import java.util.Arrays;

public enum Rank {

    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    MISS(0, false, 0);

    private final int matchingCount;
    private final boolean bonusRequired;
    private final int prize;

    Rank(int matchingCount, boolean bonusRequired, int prize) {
        this.matchingCount = matchingCount;
        this.bonusRequired = bonusRequired;
        this.prize = prize;
    }

    public static Rank find(int matchingCount, boolean bonusMatched) {
        return Arrays.stream(values())
                .filter(rank -> rank.isMatched(
                        matchingCount,
                        bonusMatched
                ))
                .findFirst()
                .orElse(MISS);
    }

    private boolean isMatched(int matchingCount, boolean bonusMatched) {
        if (this.matchingCount != matchingCount) {
            return false;
        }

        return isBonusConditionMatched(bonusMatched);
    }

    private boolean isBonusConditionMatched(boolean bonusMatched) {
        if (matchingCount != 5) {
            return true;
        }

        return bonusMatched == bonusRequired;
    }

    public int getPrize() {
        return prize;
    }
}
