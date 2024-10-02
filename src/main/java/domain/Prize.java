package domain;

public enum Prize {

    THREE_MATCHES(3, 5000),
    FOUR_MATCHES(4, 50000),
    FIVE_MATCHES(5, 1500000),
    FIVE_MATCHES_BONUS(5, 30000000),
    SIX_MATCHES(6, 2000000000);

    private final int matchingCount;
    private final int prizeAmount;

    Prize(int matchingCount, int prizeAmount) {
        this.matchingCount = matchingCount;
        this.prizeAmount = prizeAmount;
    }

    public int getMatchingCount() {
        return matchingCount;
    }

    public int getPrizeAmount() {
        return prizeAmount;
    }
}
