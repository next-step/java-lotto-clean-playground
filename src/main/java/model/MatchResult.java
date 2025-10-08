package model;

public enum MatchResult {
    ZERO(0, 0),
    ONE(1, 0),
    TWO(2, 0),
    THREE(3, 5000),
    FOUR(4, 50000),
    FIVE(5, 150000),
    FIVE_BONUS(5, 30000000),
    SIX(6, 2000000000);

    private final int count;
    private final int reward;

    MatchResult(int count, int reward) {
        this.count = count;
        this.reward = reward;
    }

    public int getCount() {
        return count;
    }

    public int getReward() {
        return reward;
    }

    public static MatchResult fromCount(int count, boolean bonusMatch) {
        if (count == 5 && bonusMatch) {
            return FIVE_BONUS;
        }
        for (MatchResult result : values()) {
            if (result.count == count && result != FIVE_BONUS) return result;
        }
        return ZERO;
    }
}
