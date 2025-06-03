package domain;

import java.util.Arrays;
import java.util.List;

public enum Prize {
    FIRST(6, false, 2000000000, "6개 일치"),
    SECOND(5, true, 30000000, "5개 일치, 보너스 볼 일치"),
    THIRD(5, false, 1500000, "5개 일치"),
    FOURTH(4, false, 50000, "4개 일치"),
    FIFTH(3, false, 5000, "3개 일치");

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

    public String getDescription() {
        return description;
    }

    public int getReward() {

        return reward;
    }
}
