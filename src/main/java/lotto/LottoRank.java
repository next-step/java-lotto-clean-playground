package lotto;

import java.util.Arrays;

public enum LottoRank {
    FIRST(6, false, 2_000_000_000L),
    SECOND(5, true, 30_000_000L),
    THIRD(5, false, 1_500_000L),
    FOURTH(4, false, 50_000L),
    FIFTH(3, false, 5_000L),
    MISS(0, false, 0L);

    private final int matchCount;
    private final boolean bonusRequired;
    private final PrizeMoney prizeMoney;

    LottoRank(int matchCount, boolean bonusRequired, long prizeMoney) {
        this.matchCount = matchCount;
        this.bonusRequired = bonusRequired;
        this.prizeMoney = new PrizeMoney(prizeMoney);
    }

    public static LottoRank from(int matchCount, boolean bonusMatched) {
        return Arrays.stream(values())
                .filter(rank -> rank.matches(matchCount, bonusMatched))
                .findFirst()
                .orElse(MISS);
    }

    private boolean matches(int matchCount, boolean bonusMatched) {
        if (bonusRequired) {
            return this.matchCount == matchCount && bonusMatched;
        }
        return this.matchCount == matchCount;
    }

    public PrizeMoney prizeMoney() {
        return prizeMoney;
    }

    public int matchCount() {
        return matchCount;
    }
}
