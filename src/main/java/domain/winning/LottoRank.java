package domain.winning;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 당첨 등수. 등수별 일치 개수와 상금을 한 곳에서 관리.
 * 선언 순서가 출력순서 이므로 3개 일치부터 선언.
 */
public enum LottoRank {
    FIFTH(3, 5_000, false),
    FOURTH(4, 50_000, false),
    THIRD(5, 1_500_000, false),
    SECOND(5, 30_000_000, true),
    FIRST(6, 2_000_000_000, false),
    MISS(0, 0, false);

    private final int matchCount;
    private final long prize;
    private final boolean bonusRequired;

    LottoRank(int matchCount, long prize, boolean bonusRequired) {
        this.matchCount = matchCount;
        this.prize = prize;
        this.bonusRequired = bonusRequired;
    }

    public static LottoRank from(int matchCount, boolean bonusMatched) {
        return Arrays.stream(values())
                .filter(LottoRank::isWinning)
                .filter(rank -> rank.matches(matchCount, bonusMatched))
                .max(Comparator.comparingLong(LottoRank::getPrize))
                .orElse(MISS);
    }

    private boolean matches(int matchCount, boolean bonusMatched) {
        return this.matchCount == matchCount && (!bonusRequired || bonusMatched);
    }

    public boolean isWinning() {
        return this != MISS;
    }

    public boolean isBonusRequired() {
        return bonusRequired;
    }

    public long calculatePrize(int count) {
        return prize * count;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public long getPrize() {
        return prize;
    }
}
