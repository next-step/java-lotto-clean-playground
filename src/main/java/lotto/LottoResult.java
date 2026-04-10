package lotto;

public enum LottoResult {
    NONE(0, 0),
    THREE(3, 5_000),
    FOUR(4, 50_000),
    FIVE(5, 1_500_000),
    BONUS(5, 30_000_000),
    SIX(6, 2_000_000_000);

    private final int matchingCount;
    private final int reward;

    LottoResult(int matchingCount, int reward) {
        this.matchingCount = matchingCount;
        this.reward = reward;
    }

    public static LottoResult valueOf(int count, boolean matchBonus) {
        if (count == 5 && matchBonus) {
            return BONUS;
        }

        for (LottoResult result : values()) {
            if (result != BONUS && result.matchingCount == count) {
                return result;
            }
        }
        return NONE;
    }

    public int getMatchingCount() {
        return matchingCount;
    }

    public int getReward() {
        return reward;
    }
}
