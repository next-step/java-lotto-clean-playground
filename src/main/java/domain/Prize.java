package domain;

import java.util.Arrays;

public enum Prize {
    FIRST(6, false, 2_000_000_000, "6개 일치"),
    SECOND(5, true, 30_000_000, "5개 일치, 보너스 볼 일치"),
    THIRD(5, false, 1_500_000, "5개 일치"),
    FOURTH(4, false, 50_000, "4개 일치"),
    FIFTH(3, false, 5_000, "3개 일치");

    private final int matchCount;
    private final boolean bonusStatus;
    private final int reward;

    private final String description;

    Prize(int matchCount, boolean bonusStatus, int reward, String description) {
        this.matchCount = matchCount;
        this.bonusStatus = bonusStatus;
        this.reward = reward;
        this.description = description;
    }

    public boolean isBonusStatus() {
        return bonusStatus;
    }

    public static Prize of(int matchCount, boolean bonusMatch) {
        return Arrays.stream(values())
                       .filter(p -> p.matchCount == matchCount)
                       .filter(p -> p.bonusStatus == bonusMatch || !p.bonusStatus)
                       .findFirst()
                       .orElse(null);
    }

    public String getDescription() {
        return description;
    }

    public int getMatchCount() {

        return matchCount;
    }

    public int getReward() {

        return reward;
    }
}
