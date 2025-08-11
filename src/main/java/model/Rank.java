package model;

import java.util.Arrays;

public enum Rank {

    FIFTH(3, false, 5_000),
    FOURTH(4, false, 50_000),
    THIRD(5, false, 1_500_000),
    SECOND(5, true, 30_000_000),
    FIRST(6, false, 2_000_000_000);

    private final int matchCount;
    private final boolean bonusMatched;
    private final int prize;

    Rank(int matchCount, boolean bonusMatched, int prize) {
        this.matchCount = matchCount;
        this.bonusMatched = bonusMatched;
        this.prize = prize;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isBonusMatched() {
        return bonusMatched;
    }

    public int getPrize() {
        return prize;
    }

    public static Rank of(int matchCount, boolean bonusMatched) {
        return Arrays.stream(Rank.values())
            .filter(r -> r.matchCount == matchCount && r.bonusMatched == bonusMatched)
            .findFirst()
            .orElse(null);
    }
}
