package lotto.global;

import java.util.Arrays;

public enum Rank {
    FIRST(2_000_000_000, 6, false),
    SECOND(30_000_000, 5, true),
    THIRD(1_500_000, 5, false),
    FOURTH(50_000, 4, false),
    FIFTH(5_000, 3, false),
    UN_LUCK(0, 0, false);

    private final int reward;
    private final int match;
    private final boolean bonus;

    Rank(final int reward, final int match, final boolean bonus) {
        this.reward = reward;
        this.match = match;
        this.bonus = bonus;
    }

    public static Rank getRank(int match, boolean bonus) {
        return Arrays.stream(Rank.values())
            .filter(rank -> rank.match == match && rank.bonus == bonus)
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
