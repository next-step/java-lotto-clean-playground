package domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum LottoRank {
    FIRST((matchCount, bonusMatch) -> matchCount == 6, 6, 2000000000),
    SECOND((matchCount, bonusMatch) -> matchCount == 5 && bonusMatch, 5, 30000000),
    THIRD((matchCount, bonusMatch) -> matchCount == 5 && !bonusMatch, 5, 1500000),
    FOURTH((matchCount, bonusMatch) -> matchCount == 4, 4, 50000),
    FIFTH((matchCount, bonusMatch) -> matchCount == 3, 3, 5000),
    MISS((matchCount, bonusMatch) -> matchCount < 3, 0, 0);

    private final BiPredicate<Integer, Boolean> condition;
    private final int matchCount;
    private final int prize;

    LottoRank(BiPredicate<Integer, Boolean> condition, int matchCount, int prize) {
        this.condition = condition;
        this.matchCount = matchCount;
        this.prize = prize;
    }

    public static LottoRank findRank(int matchCount, boolean bonusMatch) {
        return Arrays.stream(values())
                .filter(rank -> rank.condition.test(matchCount, bonusMatch))
                .findFirst()
                .orElse(MISS);
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrize() {
        return prize;
    }
}
