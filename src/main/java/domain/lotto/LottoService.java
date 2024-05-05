package domain.lotto;

import domain.lottoTicket.LottoTicket;
import domain.lottoTicket.LottoTicketService;
import java.util.ArrayList;
import java.util.List;

public class LottoService {
    private final int LOTTO_PRICE = 1000;
    private final LottoTicketService lottoTicketService = new LottoTicketService();
    public LottoService() {
    }

    public int countLottoTickets(int money) {
        int lottoTicketCount = (int) money / LOTTO_PRICE;

        return lottoTicketCount;
    }

    public List<LottoTicket> generateLottoTicketList(Lotto lotto) {
        List<LottoTicket> lottoTicketList = new ArrayList<>();
        for(int i = 0; i < lotto.getLottoTicketCount(); i++) {
            LottoTicket lottoTicket = new LottoTicket(lottoTicketService.generateLottoTicket());
            lottoTicketList.add(lottoTicket);
        }
        return lottoTicketList;
    }

    public List<Integer> makeLottoWinningStatistic(List<List<Integer>> lottoTickets,
                                                   List<Integer> winningNumbers) {
        List<Integer> winningStatistics = new ArrayList<>();

        lottoTickets.forEach(lottoTicket -> winningStatistics.add(countLottoWinningNumber(lottoTicket, winningNumbers)));

        return winningStatistics;
    }

    public int countLottoWinningNumber(List<Integer> lottoTicket,
                                      List<Integer> winningNumbers) {
        return (int) lottoTicket.stream().filter(winningNumbers::contains).count();
    }
}
