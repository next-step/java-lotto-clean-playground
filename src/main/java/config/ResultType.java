package config;

public enum ResultType {

    MATCH_THREE("3개 일치", 5000),
    MATCH_FOUR("4개 일치", 50000),
    MATCH_FIVE("5개 일치", 1500000),
    MATCH_SIX("6개 일치", 2000000000);

    private final String description;
    private final long winningPrice;

    ResultType(final String description, final long winningPrice) {
        this.description = description;
        this.winningPrice = winningPrice;
    }

    public String getDescription() {
        return description;
    }

    public long getWinningPrice() {
        return winningPrice;
    }

}
