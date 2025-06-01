package lotto.model;

public enum Rank {
    FIFTH(3, 5_000, false, "3개 일치 (5000원)"),
    FOURTH(4, 50_000, false, "4개 일치 (50000원)"),
    THIRD(5, 1_500_000, false, "5개 일치 (1500000원)"),
    SECOND(5, 30_000_000, true, "5개 일치, 보너스 볼 일치(30000000원)"),
    FIRST(6, 2_000_000_000, false, "6개 일치 (2000000000원)");

    private final int matchCount;
    private final int prize;
    private final boolean isBonus;
    private final String display;

    Rank(int matchCount, int prize, boolean isBonus, String display) {
        this.matchCount = matchCount;
        this.prize = prize;
        this.isBonus = isBonus;
        this.display = display;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrize() {
        return prize;
    }

    public boolean isBonus() {
        return isBonus;
    }

    public String getDisplay() {
        return display;
    }

    public static Rank valueOf(int count, boolean isBonus) {
        if (count == 6) return FIRST;
        if (count == 5 && isBonus) return SECOND;
        if (count == 5) return THIRD;
        if (count == 4) return FOURTH;
        if (count == 3) return FIFTH;
        return null;
    }

}
