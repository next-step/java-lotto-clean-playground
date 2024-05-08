package domain.lottoWinningStatistics;

import domain.Rank;
import java.util.HashMap;
import java.util.Map;

public class LottoWinningStatistics {
    private final Map<Rank, Integer> rankStatistic;
    private final double returnOfInvestment;

    public LottoWinningStatistics(Map<Rank, Integer> rankStatistic,
                                  double returnOfInvestment) {
        this.rankStatistic = rankStatistic;
        this.returnOfInvestment = returnOfInvestment;
    }

    public double getReturnOfInvestment() {
        return returnOfInvestment;
    }

    public Map<Rank, Integer> getRankStatistic() {
        return rankStatistic;
    }
}
