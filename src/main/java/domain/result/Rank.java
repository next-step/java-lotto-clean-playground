package domain.result;

import java.util.Arrays;
import java.util.List;

public enum Rank {
    FIRST(6, false, 2_000_000_000L, 5),
    SECOND(5, true, 30_000_000L, 4),
    THIRD(5, false, 1_500_000L, 3),
    FOURTH(4, false, 50_000L, 2),
    FIFTH(3, false, 5_000L, 1),
    MISS(0, false, 0L, 0);

    private final int matchCount;
    private final boolean bonusRequired;
    private final long prize;
    private final int order;

    Rank(int matchCount, boolean bonusRequired, long prize, int order) {
        this.matchCount = matchCount;
        this.bonusRequired = bonusRequired;
        this.prize = prize;
        this.order = order;
    }

    public static Rank from(int matchCount, boolean bonusMatched) {
        return Arrays.stream(values())
                     .filter(rank -> rank.matches(matchCount, bonusMatched))
                     .findFirst()
                     .orElse(MISS);
    }

    private boolean matches(int matchCount, boolean bonusMatched) {
        if (this == SECOND) {
            return matchCount == this.matchCount && bonusMatched;
        }

        if (this == THIRD) {
            return matchCount == this.matchCount && !bonusMatched;
        }

        return this != MISS && matchCount == this.matchCount;
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

    public int getOrder() {
        return order;
    }
}
