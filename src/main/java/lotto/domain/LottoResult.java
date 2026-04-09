package lotto.domain;

public enum LottoResult {
    NONE(0),
    THREE(5000),
    FOUR(5_0000),
    FIVE(150_0000),
    FIVE_BONUS(3000_0000),
    SIX(20_0000_0000);

    public final int reward;

    LottoResult(int reward) {
        this.reward = reward;
    }
}
