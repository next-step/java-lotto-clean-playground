package domain;

import java.util.Arrays;
import java.util.List;

public enum LottoRank {
    MISS(0,0),
    FIFTH(3, 5000),
    FOURTH(4,50000),
    THIRD(5, 1500000),
    SECOND(5, 30000000),
    FIRST(6, 2000000000);

    private final int matchCount;
    private final long prize;

    LottoRank(int matchCount, long prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    public static LottoRank from(int matchCount, boolean bonusMatched) {
        if (matchCount == 6) return FIRST;
        if (matchCount == 5 && bonusMatched) return SECOND;
        if (matchCount == 5) return THIRD;
        if (matchCount == 4) return FOURTH;
        if (matchCount == 3) return FIFTH;
        return MISS;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public long getPrize() {
        return prize;
    }

    public static List<LottoRank> winningRanks() {
        return Arrays.stream(values())
                .filter(rank -> rank != MISS)
                .toList();
    }
}
