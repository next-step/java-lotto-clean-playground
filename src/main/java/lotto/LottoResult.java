package lotto;

public enum LottoResult {
    NONE(0),
    THREE(5_000),
    FOUR(50_000),
    FIVE(1_500_000),
    SIX(2_000_000_000);

    private final int reward;

    LottoResult(int reward) {
        this.reward = reward;
    }

    public int getReward(){
        return reward;
    }
}
