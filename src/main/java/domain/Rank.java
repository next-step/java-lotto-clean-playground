package domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Rank {
    FIRST(6, 2_000_000_000, (matchCount, matchBonus) -> matchCount == 6),
    SECOND(5, 30_000_000, (matchCount, matchBonus) -> matchCount == 5 && matchBonus),
    THIRD(5, 1_500_000, (matchCount, matchBonus) -> matchCount == 5 && !matchBonus),
    FOURTH(4,50_000, (matchCount, matchBonus) -> matchCount == 4),
    FIFTH(3, 5_000, (matchCount, matchBonus) -> matchCount == 3),
    NONE(0, 0, (matchCount, matchBonus) -> matchCount < 3);

    private final int matchCount;
    private final long prize;
    private final BiPredicate<Integer, Boolean> matchingRule;

    Rank(int matchCount, long prize, BiPredicate<Integer, Boolean> matchingRule) {
        this.matchCount = matchCount;
        this.prize = prize;
        this.matchingRule = matchingRule;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public long getPrize() {
        return prize;
    }

    public boolean isMatched(int matchCount, boolean matchBonusBall) {
        return matchingRule.test(matchCount, matchBonusBall);
    }

    public static Rank matchCountOf(int matchCount, boolean matchBonusBall) {
        return Arrays.stream(values())
                .filter(rank -> rank.isMatched(matchCount, matchBonusBall))
                .findFirst()
                .orElse(NONE);
    }
}
