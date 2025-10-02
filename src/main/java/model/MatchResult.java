package model;

public enum MatchResult {
    ZERO(0, "0개 일치", 0),
    ONE(1, "1개 일치", 0),
    TWO(2, "2개 일치", 0),
    THREE(3, "3개 일치", 5000),
    FOUR(4, "4개 일치", 50000),
    FIVE(5, "5개 일치", 150000),
    FIVE_BONUS(5, "5개 + 보너스 일치", 30000000),
    SIX(6, "6개 일치", 2000000000);

    private final int count;
    private final String description;
    private final int reward;

    MatchResult(int count, String description, int reward) {
        this.count = count;
        this.description = description;
        this.reward = reward;
    }

    public int getCount() {
        return count;
    }

    public String getDescription() {
        return description;
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
