package lotto;

import java.util.Arrays;

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

        return Arrays.stream(values())
                .filter(result -> result != BONUS && result != NONE)
                .filter(result -> result.matchingCount == count)
                .findFirst()
                .orElse(NONE);
    }

    public boolean isDisplayable() {
        return this != NONE;
    }

    public int getMatchingCount() {
        return matchingCount;
    }

    public int getReward() {
        return reward;
    }
}
