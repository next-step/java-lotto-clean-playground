package model;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum Rank {
    FIRST(6, 2_000_000_000, false),
    SECOND(5, 30_000_000, true),
    THIRD(5, 1_500_000, false),
    FOURTH(4, 50_000, false),
    FIFTH(3, 5_000, false),
    NONE(0, 0, false);

    private final int matchCount;
    private final int prize;
    private final boolean requiresBonusMatch;

    Rank(int matchCount, int prize, boolean requiresBonusMatch) {
        this.matchCount = matchCount;
        this.prize = prize;
        this.requiresBonusMatch = requiresBonusMatch;
    }

    private static final Map<String, Rank> RANK_CACHE = Arrays.stream(values())
            .collect(Collectors.toMap(Rank::generateKey, rank -> rank));

    public static Rank of(int matchCount, boolean matchBonus) {
        String key = generateLookupKey(matchCount, matchBonus);
        return RANK_CACHE.getOrDefault(key, NONE);
    }

    private static String generateLookupKey(int matchCount, boolean matchBonus) {
        if (matchCount == 5) {
            return matchCount + "b" + matchBonus;
        }
        return String.valueOf(matchCount);
    }

    private String generateKey() {
        if (this.matchCount == 5) {
            return this.matchCount + "b" + this.requiresBonusMatch;
        }
        return String.valueOf(this.matchCount);
    }

    public int getPrize() {
        return prize;
    }

    public int getMatchCount() {
        return this.matchCount;
    }
}
