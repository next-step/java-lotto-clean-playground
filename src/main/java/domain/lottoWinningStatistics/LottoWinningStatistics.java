package domain.lottoWinningStatistics;

import domain.Rank;
import java.util.Map;

public record LottoWinningStatistics(Map<Rank, Integer> rankStatistic, double returnOfInvestment) {
}
