package domain.winning;

import java.util.Arrays;

/**
 * 당첨 등수. 등수별 일치 개수와 상금을 한 곳에서 관리.
 * 선언 순서가 출력순서 이므로 3개 일치부터 선언.
 */
public enum LottoRank {
    FIFTH(3, 5_000),
    FOURTH(4, 50_000),
    THIRD(5, 1_500_000),
    SECOND(5, 30_000_000),
    FIRST(6, 2_000_000_000),
    MISS(0, 0);

    private final int matchCount;
    private final long prize;

    LottoRank(int matchCount, long prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    public static LottoRank from(int matchCount, boolean bonusMatched) {
        if(matchCount == SECOND.matchCount && bonusMatched) {
            return SECOND;
        }

        return Arrays.stream(values())
                .filter(rank -> rank != SECOND)
                .filter(rank -> rank.matchCount == matchCount)
                .findFirst()
                .orElse(MISS);
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
