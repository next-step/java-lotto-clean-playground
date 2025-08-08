package lotto;

public enum Rank {
    THREE(3, 5_000),
    FOUR(4, 50_000),
    FIVE(5, 1_500_000),
    SECOND(5, 30_000_000),
    SIX(6, 2_000_000_000);

    private final int matchCount;
    private final int prize;

    Rank(int matchCount, int prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrize() {
        return prize;
    }

    public static Rank from(int matchCount, boolean bonusMatched) {
        if (matchCount == 6) return SIX;
        if (matchCount == 5) return bonusMatched ? SECOND : FIVE;
        if (matchCount == 4) return FOUR;
        if (matchCount == 3) return THREE;
        return null;
    }
}


