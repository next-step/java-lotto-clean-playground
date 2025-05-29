package domain;

import java.util.Arrays;
import java.util.Optional;

public enum MatchResult {
    THREE(3, 5000),
    FOUR(4, 50000),
    FIVE(5, 1500000),
    SIX(6, 2000000000);

    private final int matchCount;
    private final int prize;

    MatchResult(int matchCount, int prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrize() {
        return prize;
    }

    public static Optional<MatchResult> from(int matchCount) {
        return Arrays.stream(values())
            .filter(result -> result.matchCount == matchCount)
            .findFirst();
    }
}
