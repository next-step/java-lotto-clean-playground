package lotto;

import java.util.Arrays;

public enum LottoRank {
    FIRST(6, 2_000_000_000L),
    SECOND(5, 1_500_000L),
    THIRD(4, 50_000L),
    FOURTH(3, 5_000L),
    MISS(0, 0L);

    private final int matchCount;
    private final PrizeMoney prizeMoney;

    LottoRank(int matchCount, long prizeMoney) {
        this.matchCount = matchCount;
        this.prizeMoney = new PrizeMoney(prizeMoney);
    }

    public static LottoRank from(int matchCount) {
        return Arrays.stream(values())
                .filter(rank -> rank.matchCount == matchCount)
                .findFirst()
                .orElse(MISS);
    }

    public PrizeMoney prizeMoney() {
        return prizeMoney;
    }

    public int matchCount() {
        return matchCount;
    }
}
