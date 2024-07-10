package global;

import java.util.Arrays;

public enum Rank {

    FIRST_PLACE(2_000_000_000, 6),
    SECOND_PLACE(1_500_000, 5),
    THIRD_PLACE(50_000, 4),
    FOURTH_PLACE(5_000, 3),
    LAST_PLACE(0, 0);

    private final int reward;
    private final int correctCnt;

    Rank(final int reward, final int correctCnt) {
        this.reward = reward;
        this.correctCnt = correctCnt;
    }

    public static Rank findPlace(int correct) {
        return Arrays.stream(Rank.values())
                .filter(r -> r.correctCnt == correct)
                .findFirst()
                .orElse(LAST_PLACE);
    }

    public int getReward() {
        return reward;
    }

    public int getCorrectCnt() {
        return correctCnt;
    }
}
