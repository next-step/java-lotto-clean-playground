package domain.lotto.wrappers;

import domain.lotto.Rank;

import java.util.Collections;
import java.util.List;

import static domain.lotto.Rank.*;

public class Result {
    private final int threeCorrectCount;
    private final int fourCorrectCount;
    private final int fiveCorrectCount;
    private final int sixCorrectCount;

    public Result(List<CorrectCount> correctCounts) {
        List<Integer> correctCountList = correctCounts.stream().map(CorrectCount::getValue).toList();
        this.threeCorrectCount = Collections.frequency(correctCountList, THREE_CORRECT.getCorrectCount());
        this.fourCorrectCount = Collections.frequency(correctCountList, FOUR_CORRECT.getCorrectCount());
        this.fiveCorrectCount = Collections.frequency(correctCountList, FIVE_CORRECT.getCorrectCount());
        this.sixCorrectCount = Collections.frequency(correctCountList, SIX_CORRECT.getCorrectCount());
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
        int expense = ticketCount.getValue() * Payment.TICKET_PRICE;
        int income = THREE_CORRECT.getPrizeMoney() * this.threeCorrectCount
                + FOUR_CORRECT.getPrizeMoney() * this.fourCorrectCount
                + FIVE_CORRECT.getPrizeMoney() * this.fiveCorrectCount
                + SIX_CORRECT.getPrizeMoney() * this.sixCorrectCount;

        return  new ProfitRate(expense, income);
    }
}
