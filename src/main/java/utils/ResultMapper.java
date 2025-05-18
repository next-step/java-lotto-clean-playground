package utils;

import domain.LottoStatistics;
import domain.Profit;
import domain.Rank;
import dto.MatchResultDto;
import dto.ProfitDto;
import dto.WinningResultDto;

import java.util.Arrays;
import java.util.List;

public class ResultMapper {

    public static WinningResultDto toWinningResultDto(LottoStatistics statistics) {
        return new WinningResultDto(winningMatches(statistics));
    }

    private static List<MatchResultDto> winningMatches(LottoStatistics statistics) {
        return Arrays.stream(Rank.values())
                .filter(Rank::isWinning)
                .map(rank -> toMatchResultDto(statistics, rank))
                .toList();
    }

    private static MatchResultDto toMatchResultDto(LottoStatistics statistics, Rank rank) {
        return new MatchResultDto(
                rank.getMatchCount(),
                rank == Rank.SECOND,
                rank.getPrize(),
                statistics.countOf(rank)
        );
    }

    public static ProfitDto toProfitDto(Profit profit) {
        return new ProfitDto(profit.rate(), profit.isLoss());
    }
}
