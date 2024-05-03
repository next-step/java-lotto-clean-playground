package config;

import java.util.Arrays;

public enum ResultType {

    MATCH_ZERO(0, "0개 일치", 0),
    MATCH_THREE(3, "3개 일치", 5000),
    MATCH_FOUR(4, "4개 일치", 50000),
    MATCH_FIVE(5, "5개 일치", 1500000),
    MATCH_SIX(6, "6개 일치", 2000000000);

    private final int matchCount;
    private final String description;
    private final long winningPrice;

    ResultType(final int matchCount, final String description, final long winningPrice) {
        this.matchCount = matchCount;
        this.description = description;
        this.winningPrice = winningPrice;
    }

    public static ResultType getResultType(int matchCount) {
        return Arrays.stream(ResultType.values())
                .filter(resultType -> resultType.getMatchCount() == matchCount)
                .findFirst()
                .orElse(MATCH_ZERO);
    }

    public int getMatchCount() {
        return matchCount;
    }

    public String getDescription() {
        return description;
    }

    public long getWinningPrice() {
        return winningPrice;
    }

}
