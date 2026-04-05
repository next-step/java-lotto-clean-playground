package dto;

import domain.Rank;

public record WinningResult(String message, int count) {
    public static WinningResult from(Rank rank, int count) {
        if (rank == Rank.FIVE_BONUS_MATCH) {
            return new WinningResult("5개 일치, 보너스 볼 일치(" + rank.getPrizeMoney() + "원)", count);
        }
        return new WinningResult(rank.getMatchCount() + "개 일치 (" + rank.getPrizeMoney() + "원)", count);
    }
}
