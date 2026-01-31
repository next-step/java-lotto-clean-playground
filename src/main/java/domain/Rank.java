package domain;

import java.util.Arrays;
import java.util.Optional;

import static java.util.Optional.of;

public enum Rank {
    THREE(3, 5000),
    FOUR(4, 50000),
    FIVE(5, 1500000),
    BONUS(5, 30000000),
    SIX(6, 2000000000);

    private final int matchCount;
    private final long prize;

    Rank(int matchCount, long prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    public long prize() {
        return prize;
    }

    public static Optional<Rank> from(int matchCount, boolean bonusMatched) {
        if (matchCount == 5 && bonusMatched) {
            return of(BONUS);
        }
        return Arrays.stream(values())
                .filter(r -> r != BONUS)
                .filter(r -> r.matchCount == matchCount)
                .findFirst();
    }
}
