package domain;

import java.util.List;

public class Statistic {
    public WinningResultDto getWinningResult(List<LottoTicket> lottoTickets, List<Integer> winningNumbers) {
        WinningResultDto result = new WinningResultDto();
        for (LottoTicket lottoTicket : lottoTickets) {
            int matchedCount = lottoTicket.getMatchedNumbers(winningNumbers);
            result.addMatchCount(matchedCount);
        }

        return result;
    }

    public double getRevenue(int money, WinningResultDto winningResult) {
        double prize = WinningPrizeDto.THREE_MATCH_PRIZE * winningResult.getThreeMatchCount()
                     + WinningPrizeDto.FOUR_MATCH_PRIZE  * winningResult.getFourMatchCount()
                     + WinningPrizeDto.FIVE_MATCH_PRIZE  * winningResult.getFiveMatchCount()
                     + WinningPrizeDto.SIX_MATCH_PRIZE   * winningResult.getSixMatchCount();
        return prize/money;
    }
}
