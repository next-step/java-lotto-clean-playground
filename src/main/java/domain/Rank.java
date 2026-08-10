package domain;

public enum Rank {
    FIRST_PLACE(6, 2000000000, false),
    SECOND_PLACE_BONUS(5, 30000000, true),
    SECOND_PLACE(5, 1500000, false),
    THIRD_PLACE(4, 50000, false),
    FOURTH_PLACE(3, 5000, false),
    MISS(0, 0, false);

    private final int matchBallNum;
    private final int prize;
    private final boolean hasBonusBall;

    Rank(int matchBallNum, int prize, boolean hasBonusBall) {
        this.matchBallNum = matchBallNum;
        this.prize = prize;
        this.hasBonusBall = hasBonusBall;
    }

    public static Rank getRank(int matchBallNum, boolean hasBonusBall) {
        if (matchBallNum == FIRST_PLACE.getMatchBallNum()) { return FIRST_PLACE; }
        if (matchBallNum == SECOND_PLACE_BONUS.getMatchBallNum() && hasBonusBall) { return SECOND_PLACE_BONUS; }
        if (matchBallNum == SECOND_PLACE.getMatchBallNum() && !hasBonusBall) { return SECOND_PLACE; }
        if (matchBallNum == THIRD_PLACE.getMatchBallNum()) { return THIRD_PLACE; }
        if (matchBallNum == FOURTH_PLACE.getMatchBallNum()) { return FOURTH_PLACE; }
        return MISS;
    }

    public int getMatchBallNum() {
        return matchBallNum;
    }

    public int getPrize() {
        return prize;
    }
}
