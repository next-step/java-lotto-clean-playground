package domain.wrappers;

import java.util.Collections;
import java.util.List;

import static domain.LottoConstants.*;

public class LottoResult {
    public static final int THREE_CORRECT = 3;
    public static final int FOUR_CORRECT = 4;
    public static final int FIVE_CORRECT = 5;
    public static final int SIX_CORRECT = 6;

    private final int threeCorrectCount;
    private final int fourCorrectCount;
    private final int fiveCorrectCount;
    private final int sixCorrectCount;

    public LottoResult(List<CorrectCount> correctCounts) {
        List<Integer> correctCountList = correctCounts.stream().map(CorrectCount::getValue).toList();
        this.threeCorrectCount = Collections.frequency(correctCountList, THREE_CORRECT);
        this.fourCorrectCount = Collections.frequency(correctCountList, FOUR_CORRECT);
        this.fiveCorrectCount = Collections.frequency(correctCountList, FIVE_CORRECT);
        this.sixCorrectCount = Collections.frequency(correctCountList, SIX_CORRECT);
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
        int expense = ticketCount.getValue() * PRICE_OF_ONE_TICKET;
        int income = THREE_CORRECT_PRIZE_MONEY * this.threeCorrectCount
                + FOUR_CORRECT_PRIZE_MONEY * this.fourCorrectCount
                + FIVE_CORRECT_PRIZE_MONEY * this.fiveCorrectCount
                + SIX_CORRECT_PRIZE_MONEY * this.sixCorrectCount;

        return  new ProfitRate(expense, income);
    }
}
