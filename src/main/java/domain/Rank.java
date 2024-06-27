package domain;

public enum Rank {

    FIRST_PLACE(6, 2000000000),
    SECOND_PLACE(5,1500000),
    THIRD_PLACE(4,50000),
    FOURTH_PLACE(3,5000),
    LAST_PLACE(0, 0);

    private final int scoreCutoff;
    private final int prizeMoney;


    Rank(int scoreCutoff, int prizeMoney) {
        this.scoreCutoff = scoreCutoff;
        this.prizeMoney = prizeMoney;
    }

    public int getScoreCutoff() {
        return scoreCutoff;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public static Rank getByScore(Score score) {
        for (Rank rank : Rank.values()) {
            if (rank.getScoreCutoff() == score.getValue()) {
                return rank;
            }
        }
        return LAST_PLACE;
    }

}
