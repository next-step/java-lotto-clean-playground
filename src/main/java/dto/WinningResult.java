package dto;

import domain.Rank;
import domain.WinningStatistics;

import java.util.Arrays;
import java.util.List;

public record WinningResult(String message, int count) {
    public static List<WinningResult> from(WinningStatistics winningStatistics) {
        return Arrays.stream(Rank.values())
                .filter(Rank::isWinning)
                .map(rank -> createMessage(rank, winningStatistics.countOf(rank)))
                .toList();
    }

    private static WinningResult createMessage(Rank rank, int count) {
        if (rank == Rank.FIVE_BONUS_MATCH) {
            return new WinningResult("5개 일치, 보너스 볼 일치(" + rank.getPrizeMoney() + "원)", count);
        }
        return new WinningResult(rank.getMatchCount() + "개 일치 (" + rank.getPrizeMoney() + "원)", count);
    }
}
