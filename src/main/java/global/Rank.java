package global;

import java.util.Arrays;

public enum Rank {

    FIRST_PLACE(2_000_000_000, 6, false),
    SECOND_PLACE(30_000_000, 5, true),
    THIRD_PLACE(1_500_000, 5, false),
    FOURTH_PLACE(50_000, 4, false),
    FIFTH_PLACE(5_000, 3, false),
    LAST_PLACE(0, 0, false);

    private final int reward;
    private final int correctCnt;
    private final boolean hasBonusBall;

    Rank(final int reward, final int correctCnt, final boolean hasBonusBall) {
        this.reward = reward;
        this.correctCnt = correctCnt;
        this.hasBonusBall = hasBonusBall;
    }

    public static Rank findPlace(int correct, boolean hasBonusBall) {
        if (isSecondPlace(correct, hasBonusBall)) {
            return SECOND_PLACE;
        }

        return Arrays.stream(Rank.values())
                .filter(r -> r.correctCnt == correct && !r.hasBonusBall)
                .findFirst()
                .orElse(LAST_PLACE);
    }

    private static boolean isSecondPlace(int correct, boolean hasBonusBall) {
        return correct == 5 && hasBonusBall;
    }

    public int getReward() {
        return reward;
    }

    public int getCorrectCnt() {
        return correctCnt;
    }

    public boolean hasBonusBall() {
        return hasBonusBall;
    }
}
