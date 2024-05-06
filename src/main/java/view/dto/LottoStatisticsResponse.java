package view.dto;

import java.util.HashMap;
import java.util.Map;
import model.Rank;
import model.ResultStatistics;

public record LottoStatisticsResponse(
        Map<Rank, Integer> countOfRank,
        double profitRate
) {

    public static LottoStatisticsResponse from(ResultStatistics resultStatistics) {
        Map<Rank, Integer> ranksByCount = new HashMap<>();
        for (Rank rank : Rank.values()) {
            ranksByCount.put(rank, resultStatistics.countOf(rank));
        }
        return new LottoStatisticsResponse(
                ranksByCount,
                resultStatistics.calculateProfitRate()
        );
    }
}
