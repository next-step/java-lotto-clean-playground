package domain;

public enum WinningRank {
    THREE(3, 5000),
    FOUR(4, 50000),
    FIVE(5, 1500000),
    FIVE_WITH_BONUS(5, 30000000),
    SIX(6, 2000000000),
    NONE(0, 0);

    private final int matchCount;
    private final int prize;

    WinningRank(int matchCount, int prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrize() {
        return prize;
    }
}
