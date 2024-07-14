package domain;

import java.util.Arrays;

public enum Rank {

    FIRST_PLACE(6, 2000000000, false),
    SECOND_PLACE(5,1500000, true),
    THIRD_PLACE(5,1500000, false),

    FOURTH_PLACE(4,50000, false),
    FIFTH_PLACE(3,5000, false),
    LAST_PLACE(0, 0, false);

    private final int scoreCutoff;
    private final int prizeMoney;
    private final boolean isBonusBallMatching;


    Rank(int scoreCutoff, int prizeMoney, boolean isBonusBallMatching) {
        this.scoreCutoff = scoreCutoff;
        this.prizeMoney = prizeMoney;
        this.isBonusBallMatching = isBonusBallMatching;
    }

    public int getScoreCutoff() {
        return scoreCutoff;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public boolean isBonusBallMatching() {
        return isBonusBallMatching;
    }

    public static Rank getByScore(Score score) {
        return Arrays.stream(Rank.values())
            .filter(rank -> rank.getScoreCutoff() == score.value())
            .filter(rank -> isMatchingBonusBall(rank, score))
            .findFirst()
            .orElse(Rank.LAST_PLACE);
    }

    private static boolean isMatchingBonusBall(Rank rank, Score score) {
        if (rank == Rank.SECOND_PLACE || rank == Rank.THIRD_PLACE) {
            return rank.isBonusBallMatching() == score.isBonusBallMatching();
        }
        return true;
    }

}
