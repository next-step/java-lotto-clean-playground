package lotto.domain.model;

import java.util.Arrays;

public enum LottoRank {

    FIFTH(3, 5_000, "3개 일치 (5000원)"),
    FOURTH(4, 50_000, "4개 일치 (50000원)"),
    THIRD(5, 1_500_000, "5개 일치 (1500000원)"),
    // SECOND(5, 30_000_000, "5개 일치, 보너스 볼 일치 (30000000원)"),
    FIRST(6, 2_000_000_000, "6개 일치 (2000000000원)"),
    MISS(0, 0, "꽝");

    private final int matchCount;
    private final int prizeMoney;
    private final String description;

    LottoRank(int matchCount, int prizeMoney, String description) {
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
        this.description = description;
    }

    public static LottoRank valueOf(int matchCount) {
        return Arrays.stream(values())
            .filter(rank -> rank.matchCount == matchCount)
            .findFirst()
            .orElse(MISS);
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public String getDescription() {
        return description;
    }
}
