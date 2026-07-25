package domain.lotto.collection;

import domain.enums.LotteryPrize;
import domain.lotto.Lotto;
import domain.lotto.WinningLotto;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoTickets {

    private final List<Lotto> tickets;

    public LottoTickets(List<Lotto> tickets) {
        this.tickets = tickets;
    }

    public WinningStatistics match(WinningLotto winningLotto) {
        Map<LotteryPrize, Integer> statistics = new EnumMap<>(LotteryPrize.class);

        for (LotteryPrize prize : LotteryPrize.values()) {
            statistics.put(prize, 0);
        }

        for (Lotto ticket : tickets) {
            statistics.merge(winningLotto.match(ticket), 1, Integer::sum);
        }

        return new WinningStatistics(statistics);
    }

    public List<Lotto> getTickets() {
        return tickets;
    }
}
