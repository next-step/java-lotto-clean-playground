package lotto;

public enum LottoResult {
    NONE(0, 0),
    THREE(3, 5_000),
    FOUR(4, 50_000),
    FIVE(5, 1_500_000),
    SIX(6, 2_000_000_000);

    private final int matchingCount;
    private final int reward;

    LottoResult(int matchingCount, int reward) {
        this.matchingCount = matchingCount;
        this.reward = reward;
    }

    public static LottoResult valueOf(int count) {
        for (LottoResult result : values()) {
            if (result.matchingCount == count) {
                return result;
            }
        }
        // 3개 미만은 모두 NONE으로 처리
        if (count >= 0 && count < 3) {
            return NONE;
        }
        throw new IllegalArgumentException("유효하지 않은 당첨 개수입니다: " + count);
    }

    public int getReward(){
        return reward;
    }
}
