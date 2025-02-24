package domain;

import java.util.Arrays;

public enum LottoRank {
    FIRST(6, false, 1, 2_000_000_000),
    SECOND(5, true, 2, 30_000_000),
    THIRD(5, false, 3, 1_500_000),
    FOURTH(4, false, 4, 50_000),
    FIFTH(3, false, 5, 5_000),
    NONE(0, false, 0, 0);

    private final int matchCount;
    private final boolean hasBonus;
    private final int rank;
    private final int prizeMoney;

    LottoRank(int matchCount, boolean hasBonus, int rank, int prizeMoney) {
        this.matchCount = matchCount;
        this.hasBonus = hasBonus;
        this.rank = rank;
        this.prizeMoney = prizeMoney;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getRank() {
        return rank;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public boolean hasBonus() {
        return hasBonus;
    }

    public int getHasValue() {
        if (hasBonus) {
            return 1;
        }
        return 0;
    }

    public static int getPrizeMoneyByRank(int userRank) {
        return Arrays.stream(LottoRank.values())
                .filter(rank -> rank.rank == userRank)
                .findFirst()
                .orElse(NONE)
                .getPrizeMoney();
    }

    public static LottoRank getMatchedRankByMatchCount(int matchCount, boolean hasBonus) {
        return Arrays.stream(LottoRank.values())
                .filter(rank -> rank.matchCount == matchCount && rank.hasBonus == hasBonus)
                .findFirst()
                .orElse(NONE);
    }

    public static LottoRank getMatchedRankByRank(int userRank) {
        return Arrays.stream(LottoRank.values())
                .filter(rank -> rank.rank == userRank)
                .findFirst()
                .orElse(NONE);
    }
}
