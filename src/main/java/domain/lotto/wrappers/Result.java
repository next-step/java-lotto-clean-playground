package domain.lotto.wrappers;

import java.util.Collections;
import java.util.List;

import static domain.lotto.Rank.*;

public class Result {
    private final int threeCorrectCount;
    private final int fourCorrectCount;
    private final int fiveAndNoBonusCorrectCount;
    private final int fiveAndBonusCorrectCount;
    private final int sixCorrectCount;

    public Result(List<CorrectCount> correctCounts) {
        threeCorrectCount = Collections.frequency(correctCounts, new CorrectCount(THREE_CORRECT.getNumberOfCorrect(), false));
        fourCorrectCount = Collections.frequency(correctCounts, new CorrectCount(FOUR_CORRECT.getNumberOfCorrect(), false));
        sixCorrectCount = Collections.frequency(correctCounts, new CorrectCount(SIX_CORRECT.getNumberOfCorrect(), false));

        List<CorrectCount> fiveCorrectCounts = correctCounts
                .stream()
                .filter(correctCount -> correctCount.getCorrectCount() == 5)
                .toList();

        fiveAndBonusCorrectCount = fiveCorrectCounts.stream().filter(CorrectCount::hasBonusNumber).toList().size();
        fiveAndNoBonusCorrectCount = fiveCorrectCounts.stream().filter(correctCount -> !correctCount.hasBonusNumber()).toList().size();
    }


    public int getThreeCorrectCount() {
        return threeCorrectCount;
    }

    public int getFourCorrectCount() {
        return fourCorrectCount;
    }

    public int getFiveAndNoBonusCorrectCount() {
        return fiveAndNoBonusCorrectCount;
    }

    public int getFiveAndBonusCorrectCount() {
        return fiveAndBonusCorrectCount;
    }

    public int getSixCorrectCount() {
        return sixCorrectCount;
    }

    public ProfitRate calculateProfitRate(TicketCount ticketCount) {
        int expense = ticketCount.getValue() * Payment.TICKET_PRICE;
        int income = THREE_CORRECT.getPrizeMoney() * this.threeCorrectCount
                + FOUR_CORRECT.getPrizeMoney() * this.fourCorrectCount
                + FIVE_CORRECT.getPrizeMoney() * this.fiveAndNoBonusCorrectCount
                + FIVE_AND_BONUS_CORRECT.getPrizeMoney() * this.fiveAndBonusCorrectCount
                + SIX_CORRECT.getPrizeMoney() * this.sixCorrectCount;

        return  new ProfitRate(expense, income);
    }
}
