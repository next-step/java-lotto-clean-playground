package lotto.global;

import java.util.Arrays;

public enum Rank {
    FIRST(2_000_000_000, 6),
    SECOND(1_500_000, 5),
    THIRD(50_000, 4),
    FOURTH(5_000, 3),
    UN_LUCK(0, 0);

    private final int reward;
    private final int match;

    Rank(final int reward, final int match) {
        this.reward = reward;
        this.match = match;
    }

    public static Rank getRankByMatch(int match) {
        return Arrays.stream(Rank.values())
            .filter(rank -> rank.match == match)
            .findFirst()
            .orElse(UN_LUCK);
    }

    public static Rank[] valuesWithoutUnLuck() {
        return Arrays.stream(values())
            .filter(rank -> rank != UN_LUCK)
            .toArray(Rank[]::new);
    }

    public int getReward() {
        return reward;
    }

    public int getMatchCount() {
        return match;
    }
}
