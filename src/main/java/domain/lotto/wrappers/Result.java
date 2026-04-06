package domain.lotto.wrappers;

import domain.lotto.Rank;

import java.util.Collections;
import java.util.List;

public class Result {
    public static final int THREE_CORRECT = 3;
    public static final int FOUR_CORRECT = 4;
    public static final int FIVE_CORRECT = 5;
    public static final int SIX_CORRECT = 6;

    private final int threeCorrectCount;
    private final int fourCorrectCount;
    private final int fiveCorrectCount;
    private final int sixCorrectCount;

    public Result(List<CorrectCount> correctCounts) {
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
        int expense = ticketCount.getValue() * Payment.TICKET_PRICE;
        int income = Rank.THREE_CORRECT.getPrizeMoney() * this.threeCorrectCount
                + Rank.FOUR_CORRECT.getPrizeMoney() * this.fourCorrectCount
                + Rank.FIVE_CORRECT.getPrizeMoney() * this.fiveCorrectCount
                + Rank.SIX_CORRECT.getPrizeMoney() * this.sixCorrectCount;

        return  new ProfitRate(expense, income);
    }
}
