package domain.lotto;

public enum Rank {
    MISS(0, 0),
    THREE_CORRECT(3,5000),
    FOUR_CORRECT(4, 50000),
    FIVE_CORRECT(5, 1500000),
    FIVE_AND_BONUS_CORRECT(5, 30000000),
    SIX_CORRECT(6, 2000000000),;

    private final int numberOfCorrect;

    private final int prizeMoney;

    Rank(int numberOfCorrect, int prizeMoney) {
        this.numberOfCorrect = numberOfCorrect;
        this.prizeMoney = prizeMoney;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public int getNumberOfCorrect() {
        return numberOfCorrect;
    }

    public static Rank valueOf(CorrectCount correctCount) {
        int numberOfCorrect = correctCount.getCorrectCount();
        boolean hasBonus = correctCount.hasBonusNumber();

        if (numberOfCorrect == THREE_CORRECT.getNumberOfCorrect()) {
            return THREE_CORRECT;
        }

        if (numberOfCorrect == FOUR_CORRECT.getNumberOfCorrect()) {
            return FOUR_CORRECT;
        }

        if (numberOfCorrect == FIVE_CORRECT.getNumberOfCorrect() && !hasBonus) {
            return FIVE_CORRECT;
        }

        if (numberOfCorrect == FIVE_CORRECT.getNumberOfCorrect() && hasBonus) {
            return FIVE_AND_BONUS_CORRECT;
        }

        if (numberOfCorrect == SIX_CORRECT.getNumberOfCorrect()) {
            return SIX_CORRECT;
        }
        return MISS;
    }
}
