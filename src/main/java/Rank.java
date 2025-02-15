import java.util.Arrays;

public enum Rank {
    FIRST(6, false, 2_000_000_000),  // 1등: 6개 일치
    SECOND(5, true, 30_000_000),      // 2등: 5개 + 보너스 볼 일치
    THIRD(5, false, 1_500_000),       // 3등: 5개 일치
    FOURTH(4, false, 50_000),         // 4등: 4개 일치
    FIFTH(3, false, 5_000),           // 5등: 3개 일치
    UNRANK(0, false, 0);              // 꽝

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

    public static int getReward(int matchCount) {
        return Arrays.stream(values())
                .filter(rank -> rank.match == matchCount)
                .map(Rank::getReward)
                .findFirst()
                .orElse(0);
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
