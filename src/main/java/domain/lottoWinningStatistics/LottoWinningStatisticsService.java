package domain.lottoWinningStatistics;

import domain.lotto.Lotto;
import domain.lottoTicket.LottoTicket;
import java.util.ArrayList;
import java.util.List;

public class LottoWinningStatisticsService {
    public int matchLottoWinningNumber(LottoTicket lottoTicket,
                                       List<Integer> winningNumbers) {
        return (int) lottoTicket.getLottoNumber().stream().filter(winningNumbers::contains).count();
    }

    public List<Integer> countLottoWinning(List<LottoTicket> lottoTickets,
                                           List<Integer> winningNumbers) {
        List<Integer> winningLottoTicket = new ArrayList<>();

        lottoTickets.forEach(lottoTicket -> winningLottoTicket.add(matchLottoWinningNumber(lottoTicket, winningNumbers)));

        return winningLottoTicket;
    }

    public int caculateWinnings(List<Integer> lottoWinnerCount) {
        int winnings = 0;

        winnings += (int) lottoWinnerCount.stream().filter(count -> count == 3).count() * 5000;
        winnings += (int) lottoWinnerCount.stream().filter(count -> count == 4).count() * 50000;
        winnings += (int) lottoWinnerCount.stream().filter(count -> count == 5).count() * 1500000;
        winnings += (int) lottoWinnerCount.stream().filter(count -> count == 6).count() * 2000000000;

        return winnings;
    }

    public double caculateReturnOfInvestment(Lotto lotto, int winnings) {
        double returnOfInvestment = (double) winnings / lotto.getLottoMoney();
        return returnOfInvestment;
    }
}
