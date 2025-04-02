package domain;

import java.util.Arrays;

public enum Prize {
    FIFTH_PRIZE(3, false, 5_000),
    FOURTH_PRIZE(4, false, 50_000),
    THIRD_PRIZE(5, false, 1_500_000),
    SECOND_PRIZE(5, true, 30_000_000),
    FIRST_PRIZE(6, false, 2_000_000_000),
    NONE(0, false, 0);

    private final int matchCount;
    private final boolean bonusMatch;
    private final long prizeMoney;

    Prize(int matchCount, final boolean bonusMatch, long prizeMoney) {
        this.matchCount = matchCount;
        this.bonusMatch = bonusMatch;
        this.prizeMoney = prizeMoney;
    }

    public static Prize of(int matchCount, boolean isBonusMatched) {
        return Arrays.stream(values())
                .filter(prize -> prize.matchCount == matchCount)
                .filter(prize -> prize.bonusMatch == isBonusMatched)
                .findFirst()
                .orElse(NONE);
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isBonusMatch() {
        return bonusMatch;
    }

    public long getPrizeMoney() {
        return prizeMoney;
    }
}
