package domain.lotto;

public enum Rank {
    THREE_CORRECT(3,1000),
    FOUR_CORRECT(4, 5000),
    FIVE_CORRECT(5, 50000),
    FIVE_AND_BONUS_CORRECT(5, 1500000),
    SIX_CORRECT(6, 2000000000),;

    private final int correctCount;

    private final int prizeMoney;

    Rank(int correctCount, int prizeMoney) {
        this.correctCount = correctCount;
        this.prizeMoney = prizeMoney;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public int getCorrectCount() {
        return correctCount;
    }
}
