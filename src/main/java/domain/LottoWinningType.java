package domain;

public enum LottoWinningType {
    FIRST_PLACE(6, false, 2_000_000_000),
    SECOND_PLACE(5, true, 30_000_000),
    THIRD_PLACE(5, false, 1_500_000),
    FOURTH_PLACE(4, false, 50_000),
    FIFTH_PLACE(3, false, 5_000),
    NO_PRIZE(0, false, 0);

    private final int matchCount;
    private final boolean requiresBonus;
    private final int prize;

    LottoWinningType(int matchCount, boolean requiresBonus, int prize) {
        this.matchCount = matchCount;
        this.requiresBonus = requiresBonus;
        this.prize = prize;
    }

    public static LottoWinningType of(int matchCount, boolean matchBonus) {
        if (matchCount == FIRST_PLACE.matchCount) return FIRST_PLACE;
        if (matchCount == SECOND_PLACE.matchCount && matchBonus) return SECOND_PLACE;
        if (matchCount == THIRD_PLACE.matchCount) return THIRD_PLACE;
        if (matchCount == FOURTH_PLACE.matchCount) return FOURTH_PLACE;
        if (matchCount == FIFTH_PLACE.matchCount) return FIFTH_PLACE;
        return NO_PRIZE;
    }

    public long calculatePrize(int winningTicketCount) {
        return (long) prize * winningTicketCount;
    }

    public boolean isPrizeWinning() {
        return prize > 0;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean requiresBonus() {
        return requiresBonus;
    }

    public int getPrize() {
        return prize;
    }
}
