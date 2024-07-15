package domain;

import java.util.Arrays;

public enum Rank {

    MISS(0, 0),
    FIFTH(3, 5000),
    FOURTH(4, 50000),
    THIRD(5, 1500000),
    SECOND(5, 30000000),
    FIRST(6, 2000000000);

    private int matchCount;
    private int rewardMoney;

    private Rank(int matchCount, int rewardMoney) {
        this.matchCount = matchCount;
        this.rewardMoney = rewardMoney;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getRewardMoney() {
        return rewardMoney;
    }

    public static Rank getRank(int matchCount, boolean hasBonus) {
        if (matchCount < FIFTH.getMatchCount()) {
            return MISS;
        }

        if (matchCount == THIRD.getMatchCount() && hasBonus) {
            return SECOND;
        }

        return Arrays.stream(values()).filter(rank -> rank.getMatchCount() == matchCount).findFirst().get();
    }
}
