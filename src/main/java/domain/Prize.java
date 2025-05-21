package domain;

import java.util.Arrays;

public enum Prize {
    FIRST(6, 2_000_000_000L),
    SECOND(5, 1_500_000L),
    THIRD(4, 50_000L),
    FOURTH(3, 5_000L),
    NONE  (0, 0L);

    private final int matchCount;
    private final long prizeAmount;

    Prize(int matchCount, long prizeAmount) {
        this.matchCount = matchCount;
        this.prizeAmount = prizeAmount;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public long getPrizeAmount() {
        return prizeAmount;
    }

    public static Prize fromMatchCount(int count) {
        return Arrays.stream(values())
                .filter(p -> p.matchCount == count)
                .findFirst()
                .orElse(NONE);
    }

    public boolean isWinning() {
        return this != NONE;
    }

}

