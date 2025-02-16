import java.util.Arrays;

public enum Rank {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    UNRANK(0, false, 0);

    private final int match;
    private final boolean bonus;
    private final int reward;

    Rank(int match, boolean bonus, int reward) {
        this.match = match;
        this.bonus = bonus;
        this.reward = reward;
    }

    public static Rank getRank(int matchCount, boolean hasBonus) {
        return Arrays.stream(values())
                .filter(rank -> rank.match == matchCount && rank.bonus == hasBonus)
                .findFirst()
                .orElse(UNRANK);
    }

    public int getReward() {
        return reward;
    }

    public int getMatch() {
        return match;
    }

    public boolean hasBonus() {
        return bonus;
    }
}
