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
        List<MatchResultDto> matches = Arrays.stream(Rank.values())
                .filter(Rank::isWinning)
                .map(rank -> new MatchResultDto(
                        rank.getMatchCount(),
                        rank == Rank.SECOND,
                        rank.getPrize(),
                        statistics.countOf(rank)
                ))
                .toList();

        return new WinningResultDto(matches);
    }

    public static ProfitDto toProfitDto(Profit profit) {
        return new ProfitDto(profit.rate(), profit.isLoss());
    }
}
