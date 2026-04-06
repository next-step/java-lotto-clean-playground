package domain.lotto;

public enum Rank {
    THREE_CORRECT(1000),
    FOUR_CORRECT(5000),
    FIVE_CORRECT(50000),
    FIVE_AND_BONUS_CORRECT(1500000),
    SIX_CORRECT(2000000000),;

    private final int prizeMoney;

    Rank(int prizeMoney) {
        this.prizeMoney = prizeMoney;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

}
