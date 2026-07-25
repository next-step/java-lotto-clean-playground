package domain;

import java.util.Arrays;

public enum Rank {

    FIRST(6, false, 2_000_000_000L),
    SECOND(5, true, 30_000_000L),
    THIRD(5, false, 1_500_000L),
    FOURTH(4, false, 50_000L),
    FIFTH(3, false, 5_000L),
    MISS(0, false, 0L);

    private final int matchCount;
    private final boolean bonusRequired;
    private final long prize;

    Rank(int matchCount, boolean bonusRequired, long prize) {
        this.matchCount = matchCount;
        this.bonusRequired = bonusRequired;
        this.prize = prize;
    }

    public static Rank of(int matchCount, boolean bonusMatched) {
        return Arrays.stream(values())
                .filter(rank -> rank.matches(matchCount, bonusMatched))
                .findFirst()
                .orElse(MISS);
    }

    private boolean matches(int matchCount, boolean bonusMatched) {
        return this.matchCount == matchCount && (!bonusRequired || bonusMatched);
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isBonusRequired() {
        return bonusRequired;
    }

    public long getPrize() {
        return prize;
    }
}
