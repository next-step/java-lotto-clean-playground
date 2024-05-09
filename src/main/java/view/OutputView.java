package view;

import domain.Rank;
import domain.lotto.Lotto;
import domain.lotto.LottoService;
import domain.lottoTicket.LottoTicket;
import domain.lottoWinningStatistics.LottoWinningStatistics;
import java.util.List;
import java.util.Map;

public class OutputView {
    private final LottoService lottoService = new LottoService(new InputView(), this);
    private final String ANNOUNCEMENT_WINNING = "당첨 통계";
    private final String ANNOUNCEMENT_ROI = "총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)\n";
    private final String COUNT = "개";

    public void writeLottoTickets(Lotto lotto, List<LottoTicket> lottoTickets) {
        System.out.println("\n" + lottoService.countLottoTickets(lotto.getLottoMoney()) + "개를 구매했습니다.");
        lottoTickets.forEach(this::printLottoTicket);
    }

    public void printLottoTicket(LottoTicket lottoTicket) {
        System.out.println(lottoTicket.getLottoTicketNumber().getLotoNumber());
    }

    public void writeLottoWinningStatistics(LottoWinningStatistics lottoWinningStatistics) {
        System.out.println(ANNOUNCEMENT_WINNING);
        System.out.println("---------");
        printWinning(lottoWinningStatistics.rankStatistic());
        printReturnOfInvestment(lottoWinningStatistics.returnOfInvestment());
    }

    public void printWinning(Map<Rank, Integer> map) {
        for(Rank rank : Rank.values()) {
            System.out.println(rank.getMessage() + map.get(rank) + COUNT);
        }
    }

    public void printReturnOfInvestment(double returnOfInvestment) {
        System.out.printf(ANNOUNCEMENT_ROI, returnOfInvestment);
    }
}
