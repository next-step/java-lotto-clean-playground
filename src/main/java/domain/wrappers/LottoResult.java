package domain.wrappers;

import java.util.Collections;
import java.util.List;

public class LottoResult {
    public static final int THREE_CORRECT = 3;
    public static final int FOUR_CORRECT = 4;
    public static final int FIVE_CORRECT = 5;
    public static final int SIX_CORRECT = 6;

    private final int threeCorrectCount;
    private final int fourCorrectCount;
    private final int fiveCorrectCount;
    private final int sixCorrectCount;

    public LottoResult(List<Integer> correctCounts) {
        this.threeCorrectCount = Collections.frequency(correctCounts, THREE_CORRECT);
        this.fourCorrectCount = Collections.frequency(correctCounts, FOUR_CORRECT);
        this.fiveCorrectCount = Collections.frequency(correctCounts, FIVE_CORRECT);
        this.sixCorrectCount = Collections.frequency(correctCounts, SIX_CORRECT);
    }

    public int getThreeCorrectCount() {
        return threeCorrectCount;
    }

    public int getFourCorrectCount() {
        return fourCorrectCount;
    }

    public int getFiveCorrectCount() {
        return fiveCorrectCount;
    }

    public int getSixCorrectCount() {
        return sixCorrectCount;
    }

    public ProfitRate calculateProfitRate(TicketCount ticketCount) {
        int expense = ticketCount.getValue() * 1000;
        int income = 5000 * this.threeCorrectCount
                + 50000 * this.fourCorrectCount
                + 1500000 * this.fiveCorrectCount
                + 2000000000 * this.sixCorrectCount;

        return  new ProfitRate(expense, income);
    }
}
