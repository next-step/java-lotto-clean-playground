package domain.result;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

public enum Rank {
    FIRST(
            6,
          false,
          2_000_000_000L,
          5,
          (matchCount, bonusMatched) -> matchCount == 6
    ),
    SECOND(
            5,
            true,
            30_000_000L,
            4,
            (matchCount, bonusMatched) -> matchCount == 5 && bonusMatched
    ),
    THIRD(
            5,
            false,
            1_500_000L,
            3,
            (matchCount, bonusMatched) -> matchCount == 5 && !bonusMatched
    ),
    FOURTH(
            4,
            false,
            50_000L,
            2,
            (matchCount, bonusMatched) -> matchCount == 4
    ),
    FIFTH(
            3,
            false,
            5_000L,
            1,
            (matchCount, bonusMatched) -> matchCount == 3
    ),
    MISS(
            0,
            false,
            0L,
            0,
            (matchCount, bonusMatched) -> false
    );

    private final int matchCount;
    private final boolean bonusRequired;
    private final long prize;
    private final int order;
    private final BiPredicate<Integer, Boolean> matcher;

    Rank(int matchCount, boolean bonusRequired, long prize, int order, BiPredicate<Integer, Boolean> matcher) {
        this.matchCount = matchCount;
        this.bonusRequired = bonusRequired;
        this.prize = prize;
        this.order = order;
        this.matcher = matcher;
    }

    public static Rank from(int matchCount, boolean bonusMatched) {
        return Arrays.stream(values())
                     .filter(rank -> rank.matches(matchCount, bonusMatched))
                     .findFirst()
                     .orElse(MISS);
    }

    private boolean matches(int matchCount, boolean bonusMatched) {
        return matcher.test(matchCount, bonusMatched);
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
