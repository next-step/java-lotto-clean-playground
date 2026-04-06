package lotto;

public enum LottoResult {
    NONE(0, 0),
    THREE(3, 5_000),
    FOUR(4, 50_000),
    FIVE(5, 1_500_000),
    SIX(6, 2_000_000_000);

    private final int matchingNumber;
    private final int reward;

    LottoResult(int matchingNumber, int reward) {
        this.matchingNumber = matchingNumber;
        this.reward = reward;
    }

    public int getMatchingNumber() {
        return matchingNumber;
    }

    public int getReward(){
        return reward;
    }
}
