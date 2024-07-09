package domain;

public enum WinningRank {
    THREE(3, false, 5000, "3개 일치 (5000원)"),
    FOUR(4, false, 50000, "4개 일치 (50000원)"),
    FIVE(5, false, 1500000, "5개 일치 (1500000원)"),
    FIVE_WITH_BONUS(5, true, 30000000, "5개 일치, 보너스 볼 일치 (30000000원)"),
    SIX(6, false, 2000000000, "6개 일치 (2000000000원)"),
    NONE(0, false, 0, "");

    private final int matchCount;
    private final boolean requiresBonus;
    private final int prize;
    private final String message;

    WinningRank(int matchCount, boolean requiresBonus, int prize, String message) {
        this.matchCount = matchCount;
        this.requiresBonus = requiresBonus;
        this.prize = prize;
        this.message = message;
    }

    public int getPrize() {
        return prize;
    }

    public String getMessage() {
        return message;
    }

    public static WinningRank valueOf(int matchCount, boolean bonus) {
        for (WinningRank rank : values()) {
            if (rank.matchCount == matchCount && rank.requiresBonus == bonus) {
                return rank;
            }
        }
        return NONE;
    }
}
