package domain;

import java.util.Arrays;
import java.util.Optional;

public enum LottoRank {
    THREE_MATCH(3, false, 5_000L, "3개 일치"),
    FOUR_MATCH(4, false, 50_000L, "4개 일치"),
    FIVE_MATCH(5, false, 1_500_000L, "5개 일치"),
    FIVE_MATCH_WITH_BONUS(5, true, 30_000_000L, "5개 일치, 보너스 볼 일치"),
    SIX_MATCH(6, false, 2_000_000_000L, "6개 일치");

    private static final int BONUS_MATCH_COUNT = 5;

    private final int matchCount;
    private final boolean bonusMatch;
    private final long prize;
    private final String description;

    LottoRank(int matchCount, boolean bonusMatch, long prize, String description) {
        this.matchCount = matchCount;
        this.bonusMatch = bonusMatch;
        this.prize = prize;
        this.description = description;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public long getPrize() {
        return prize;
    }

    public String getDescription() {
        return description;
    }

    private boolean matches(int matchCount, boolean bonusMatch) {
        if (this.matchCount != matchCount) {
            return false;
        }
        if (matchCount == BONUS_MATCH_COUNT) {
            return this.bonusMatch == bonusMatch;
        }
        return true;
    }

    public static Optional<LottoRank> findByMatchResult(int matchCount, boolean bonusMatch) {
        return Arrays.stream(values())
                .filter(rank -> rank.matches(matchCount, bonusMatch))
                .findFirst();
    }
}
