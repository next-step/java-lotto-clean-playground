package lotto.model;

public enum Rank {
    MATCH_3(3, 5_000, "3개 일치 (5000원)"),
    MATCH_4(4, 50_000, "4개 일치 (50000원)"),
    MATCH_5(5, 1_500_000, "5개 일치 (1500000원)"),
    MATCH_6(6, 2_000_000_000, "6개 일치 (2000000000원)");

    private final int matchCount;
    private final int prize;
    private final String display;

    Rank(int matchCount, int prize, String display) {
        this.matchCount = matchCount;
        this.prize = prize;
        this.display = display;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrize() {
        return prize;
    }

    public String getDisplay() {
        return display;
    }
}
