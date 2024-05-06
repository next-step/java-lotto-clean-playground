package config;

import java.util.Arrays;

public enum ResultType {

    FIRST(6, false, "6개 일치", 2000000000),
    SECOND(5, true, "5개 일치, 보너스 볼 일치", 30000000),
    THIRD(5, false, "5개 일치", 1500000),
    FOURTH(4, false, "4개 일치", 50000),
    FIFTH(3, false, "3개 일치", 5000),
    MATCH_ZERO(0, false, "0개 일치", 0);

    private final int matchCount;
    private final boolean hasBonus;
    private final String description;
    private final long winningPrice;

    ResultType(final int matchCount,
               final boolean hasBonus,
               final String description,
               final long winningPrice) {
        this.matchCount = matchCount;
        this.hasBonus = hasBonus;
        this.description = description;
        this.winningPrice = winningPrice;
    }

    public static ResultType getResultType(final int matchCount, final boolean hasBonus) {
        return Arrays.stream(ResultType.values())
                .filter(resultType -> resultType.matchCount == matchCount)
                .filter(resultType -> resultType.hasBonus == hasBonus)
                .findFirst()
                .orElse(MATCH_ZERO);
    }

    public String getDescription() {
        return description;
    }

    public long getWinningPrice() {
        return winningPrice;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isHasBonus() {
        return hasBonus;
    }
}
