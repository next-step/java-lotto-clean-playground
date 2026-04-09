package lotto;

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

    public static LottoResult valueOf(int matchingCount, boolean matchBonus) {
        if (matchingCount == 6) {
            return SIX;
        }
        if (matchingCount == 5 && matchBonus) {
            return BONUS;
        }
        if (matchingCount == 5) {
            return FIVE;
        }
        if (matchingCount == 4) {
            return FOUR;
        }
        if (matchingCount == 3) {
            return THREE;
        }
        if (matchingCount < 3 && matchingCount >= 0) {
            return NONE;
        }
        throw new IllegalArgumentException("유효하지 않은 당첨 개수입니다: " + matchingCount);
    }

    public int getReward() {
        return reward;
    }
}