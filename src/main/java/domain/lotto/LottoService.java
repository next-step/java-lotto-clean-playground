package domain.lotto;

import domain.lottoTicket.LottoTicket;
import domain.lottoTicket.LottoTicketService;
import domain.lottoWinningStatistics.LottoWinningStatistics;
import domain.lottoWinningStatistics.LottoWinningStatisticsService;
import java.util.ArrayList;
import java.util.List;
import view.InputView;
import view.OutputView;

public class LottoService {
    private final LottoTicketService lottoTicketService = new LottoTicketService();
    private final LottoWinningStatisticsService lottoWinningStatisticsService = new LottoWinningStatisticsService();
    private final int LOTTO_PRICE = 1000;
    private final InputView inputView;
    private final  OutputView outputView;

    public LottoService(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        int buyingMoney = inputView.readMoney();
        Lotto lotto = new Lotto(buyingMoney, countLottoTickets(buyingMoney));
        List<LottoTicket> lottoTickets = generateLottoTicketList(lotto);
        printLottoTickets(lotto, lottoTickets);
        drawLottoWinning(lotto, lottoTickets);
    }

    public int countLottoTickets(int money) {
        int lottoTicketCount = (int) money / LOTTO_PRICE;

        return lottoTicketCount;
    }

    public List<LottoTicket> generateLottoTicketList(Lotto lotto) {
        List<LottoTicket> lottoTicketList = new ArrayList<>();

        for(int i = 0; i < lotto.getLottoTicketCount(); i++) {
            LottoTicket lottoTicket = lottoTicketService.generateLottoTicket();
            lottoTicketList.add(lottoTicket);
        }
        return lottoTicketList;
    }

    public void printLottoTickets(Lotto lotto, List<LottoTicket> lottoTickets) {
        outputView.writeLottoTickets(lotto, lottoTickets);
    }

    public void drawLottoWinning(Lotto lotto, List<LottoTicket> lottoTickets) {
        List<Integer> winningNumbers = inputView.readWinningNumber();

        List<Integer> lottoWinnerCount = lottoWinningStatisticsService.countLottoWinning(lottoTickets, winningNumbers);
        int winnings = lottoWinningStatisticsService.caculateWinnings(lottoWinnerCount);
        double returnOfInvestment = lottoWinningStatisticsService.caculateReturnOfInvestment(lotto, winnings);
        LottoWinningStatistics lottoWinningStatistics = new LottoWinningStatistics(winningNumbers, lottoWinnerCount, returnOfInvestment);

        outputView.writeLottoWinningStatistics(lottoWinningStatistics);
    }
}
