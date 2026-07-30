package domain.winning;

import java.util.Arrays;

public enum Rank {
    FIFTH(3, false, 5000),
    FOURTH(4, false, 50000),
    THIRD(5, false, 1500000),
    SECOND(5, true, 30000000),
    FIRST(6, false, 2000000000),
    NONE(0, false, 0);

    private final boolean bonusMatch;
    private final int matchCount;
    private final long prize;

    Rank(int matchCount, boolean bonusMatch, long prize) {
        this.matchCount = matchCount;
        this.bonusMatch = bonusMatch;
        this.prize = prize;
    }

    public static Rank valueOf(int matchCount, boolean isBonusMatched) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.isMatchingRank(matchCount, isBonusMatched))
                .findFirst()
                .orElse(NONE);
    }

    private boolean isMatchingRank(int matchCount, boolean isBonusMatched) {
        if (this.matchCount != matchCount) {
            return false;
        }
        if (matchCount != 5) {
            return true;
        }
        return this.bonusMatch == isBonusMatched;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public long getPrize() {
        return prize;
    }

    public boolean isBonusMatch() {
        return bonusMatch;
    }
}
