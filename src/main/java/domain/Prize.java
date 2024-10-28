package domain;

public enum Prize {

    THREE_MATCHES(3, 5000),
    FOUR_MATCHES(4, 50000),
    FIVE_MATCHES(5, 1500000),
    FIVE_MATCHES_BONUS(5, 30000000),
    SIX_MATCHES(6, 2000000000);

    private final int matchingCount; //맞은 개수
    private final int prizeAmount; //상금 크기

    Prize(int matchingCount, int prizeAmount) {
        this.matchingCount = matchingCount;
        this.prizeAmount = prizeAmount;
    }

    public static Prize fromMatchCount(int matchCount, boolean isBonusMatch) {
        if (matchCount == 6) {
            return SIX_MATCHES;
        } else if (matchCount == 5 && isBonusMatch) {
            return FIVE_MATCHES_BONUS;
        } else if (matchCount == 5) {
            return FIVE_MATCHES;
        } else if (matchCount == 4) {
            return FOUR_MATCHES;
        } else if (matchCount == 3) {
            return THREE_MATCHES;
        }
        throw new IllegalArgumentException("Invalid match count: " + matchCount);
    }

    public int getMatchingCount() {
        return matchingCount;
    }

    public int getPrizeAmount() {
        return prizeAmount;
    }
}
