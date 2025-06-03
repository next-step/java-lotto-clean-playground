package domain;

import java.util.Arrays;
import java.util.List;

public enum Prize {
    FIRST(6, false, 2000000000),
    SECOND(5, true, 30000000),
    THIRD(5, false, 1500000),
    FOURTH(4, false, 50000),
    FIFTH(3, false, 5000);

    private final int matchCount;
    private final boolean bonusStatus;
    private final int reward;

    Prize(int matchCount, boolean bonusStatus, int reward) {
        this.matchCount = matchCount;
        this.bonusStatus = bonusStatus;
        this.reward = reward;
    }

    public static Prize of(int matchCount, boolean bonusMatch) {
        return Arrays.stream(values())
                        .filter(p -> p.matchCount == matchCount)
                        .filter(p -> p.bonusStatus == bonusMatch || !p.bonusStatus)
                        .findFirst()
                        .orElse(null);
    }

    public static Prize calculatePrize(Lotto lotto, List<Integer> winningNumbers, int bonusBall) {
        int matchCount = (int) lotto.getNumberValues()
                                        .stream()
                                        .filter(winningNumbers::contains)
                                        .count();
        boolean bonusMatch = lotto.getNumberValues().contains(bonusBall);
        return Prize.of(matchCount, bonusMatch);
    }

    public int getReward() {
        return reward;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isBonusStatus() {
        return bonusStatus;
    }
}
