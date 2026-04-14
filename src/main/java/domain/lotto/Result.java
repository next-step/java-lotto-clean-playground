package domain.lotto;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Result {
    private final Map<Rank, Integer> rankCounts;

    public Result(List<CorrectCount> correctCounts) {
        rankCounts = new EnumMap<>(Rank.class);

        for (Rank rank : Rank.values()) {
            rankCounts.put(rank, 0);
        }

        for (CorrectCount correctCount : correctCounts) {
            Rank rank = Rank.valueOf(correctCount);
            rankCounts.put(rank, rankCounts.get(rank) + 1);
        }
    }

    public int getCount(Rank rank) {
        return rankCounts.get(rank);
    }

    public ProfitRate calculateProfitRate(Payment payment) {
        int expense = payment.getValue();
        int income = 0;

        for (Rank rank : Rank.values()) {
            income += rank.getPrizeMoney() * this.getCount(rank);
        }

        return  new ProfitRate(expense, income);
    }
}
