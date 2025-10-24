package model;


import java.util.Arrays;

public enum MatchResult {
    ZERO(0, 0),
    THREE(3, 5000),
    FOUR(4, 50000),
    FIVE(5, 1500000),
    FIVE_BONUS(5, 30000000),
    SIX(6, 2000000000);

    private final int count;
    private final int reward;

    MatchResult(int count, int reward) {
        this.count = count;
        this.reward = reward;
    }
  
    public int getReward() {
        return reward;
    }

    public static MatchResult fromCount(int count, boolean bonusMatch) {
        if (count == 5 && bonusMatch) {
            return FIVE_BONUS;
        }

        return Arrays.stream(values())
                .filter(result -> result.count == count && result != FIVE_BONUS)
                .findFirst()
                .orElse(ZERO);
    }
}
