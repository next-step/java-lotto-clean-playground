package domain;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    public static Rank from(int matchCount, boolean bonusMatched) {
        if (matchCount == 6) {
            return FIRST;
        }

        if (matchCount == 5 && bonusMatched) {
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

        return MISS;
    }

    public static List<Rank> winningRanks() {
        return Arrays.stream(values())
                .filter(rank -> rank != MISS)
                .toList();
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
