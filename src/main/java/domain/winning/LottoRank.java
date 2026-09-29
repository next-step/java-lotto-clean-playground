package domain.winning;

import java.util.Arrays;

/**
 * 당첨 등수. 등수별 일치 개수와 상금을 한 곳에서 관리.
 * 선언 순서가 출력순서 이므로 3개 일치부터 선언.
 */
public enum LottoRank {
    THREE(3, 5000),
    FOUR(4, 50000),
    FIVE(5, 1500000),
    SIX(6, 2000000000),
    MISS(0, 0);

    private final int matchCount;
    private final long prize;

    LottoRank(int matchCount, long prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    public static LottoRank from(int matchCount) {
        return Arrays.stream(values())
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
