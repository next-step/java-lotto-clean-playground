package domain;

import java.util.Arrays;
import java.util.Optional;

public enum Prize {
    THREE(3, 5000),
    FOUR(4, 50000),
    FIVE(5, 1500000),
    SIX(6, 2000000000);

    private final int matchCount;
    private final int reward;

    Prize(int matchCount, int reward) {
        this.matchCount = matchCount;
        this.reward = reward;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getReward() {
        return reward;
    }

    public static Prize of(int matchCount) {
        return Arrays.stream(values())
                       .filter(p -> p.matchCount == matchCount)
                       .findFirst()
                       .orElse(null);
    }
}
