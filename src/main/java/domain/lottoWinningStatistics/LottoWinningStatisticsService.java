package domain.lottoWinningStatistics;

import domain.Rank;
import domain.lotto.Lotto;
import domain.lottoTicket.LottoTicket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoWinningStatisticsService {
    private static final int LOTTO_NUMBER_SIZE = 6;
    public int matchLottoWinningNumber(LottoTicket lottoTicket,
                                       List<Integer> winningNumbers) {
        return (int) lottoTicket.getLottoTicketNumber().getLotoNumber().stream().filter(winningNumbers::contains).count();
    }

    public Map<Rank, Integer> generateWinningStatistic(List<LottoTicket> lottoTickets,
                                                       List<Integer> winningNumbers,
                                                       int bonusNumber) {
        Map<Rank, Integer> rankStatistic = generateRankStatistic(lottoTickets, winningNumbers);
        return findSecondWinning(rankStatistic, lottoTickets, winningNumbers, bonusNumber);
    }

    public Map<Rank, Integer> generateRankStatistic(List<LottoTicket> lottoTickets,
                                                    List<Integer> winningNumbers) {
        Map<Rank, Integer> rankStatistic = initRankStatistic();

        lottoTickets.forEach(lottoTicket -> {
            Rank rank = Rank.of(matchLottoWinningNumber(lottoTicket, winningNumbers), hasBonusBall(winningNumbers));

            if(rank != null)
                rankStatistic.put(rank, rankStatistic.get(rank) + 1);
        });

        return rankStatistic;
    }

    public Map<Rank, Integer> initRankStatistic() {
        Map<Rank, Integer> rankStatistic = new HashMap<>();

        for(Rank rank : Rank.values()) {
            rankStatistic.put(rank, 0);
        }
        return rankStatistic;
    }

    public Map<Rank, Integer> findSecondWinning(Map<Rank, Integer> rankStatistic,
                                                List<LottoTicket> lottoTickets,
                                                List<Integer> winningNumbers,
                                                int bonusNumber) {
        List<Integer> bonusWinningNumbers = new ArrayList<>(winningNumbers);
        bonusWinningNumbers.add(bonusNumber);

        lottoTickets.forEach(lottoTicket -> {
            Rank rank = Rank.of(matchLottoWinningNumber(lottoTicket, bonusWinningNumbers), hasBonusBall(bonusWinningNumbers));
            if(rank != null)
                rankStatistic.put(rank, rankStatistic.get(rank) + 1);
        });

        return rankStatistic;
    }

    public boolean hasBonusBall(List<Integer> winningNumber) {
        if(winningNumber.size() == LOTTO_NUMBER_SIZE)
            return true;
        return false;
    }

    public double caculateReturnOfInvestment(Lotto lotto, Map<Rank, Integer> rankStatistic) {
        int winnings = caculateWinnig(rankStatistic);
        return (double) winnings / lotto.getLottoMoney();
    }

    public int caculateWinnig(Map<Rank, Integer> rankStatistic) {
        int winnings = 0;

        for(Rank rank : Rank.values()) {
            winnings += rankStatistic.get(rank) * rank.getRankMoney();
        }

        return winnings;
    }
}
