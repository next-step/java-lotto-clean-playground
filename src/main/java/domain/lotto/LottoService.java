package domain.lotto;

import domain.Rank;
import domain.lottoTicket.LottoTicket;
import domain.lottoTicket.LottoTicketService;
import domain.lottoWinningStatistics.LottoWinningStatistics;
import domain.lottoWinningStatistics.LottoWinningStatisticsService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import view.InputView;
import view.OutputView;

public class LottoService {
    private final LottoTicketService lottoTicketService = new LottoTicketService();
    private final LottoWinningStatisticsService lottoWinningStatisticsService = new LottoWinningStatisticsService();
    private final int LOTTO_SIZE = 6;
    private final int LOTTO_PRICE = 1000;
    private final InputView inputView;
    private final  OutputView outputView;

    public LottoService(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        String buyingMoney = inputView.readMoney();
        Lotto lotto = new Lotto(buyingMoney);
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

        for(int i = 0; i < countLottoTickets(lotto.getLottoMoney()); i++) {
            LottoTicket lottoTicket = lottoTicketService.generateLottoTicket();
            lottoTicketList.add(lottoTicket);
        }

        return lottoTicketList;
    }

    public void printLottoTickets(Lotto lotto, List<LottoTicket> lottoTickets) {
        outputView.writeLottoTickets(lotto, lottoTickets);
    }

    public void drawLottoWinning(Lotto lotto, List<LottoTicket> lottoTickets) {
        List<Integer> winningNumbers = readWinningNumbers();
        int bonusBall = readBonusBall(winningNumbers);

        Map<Rank, Integer> ranks = lottoWinningStatisticsService.generateWinningStatistic(lottoTickets, winningNumbers, bonusBall);
        double returnOfInvestment = lottoWinningStatisticsService.caculateReturnOfInvestment(lotto, ranks);
        LottoWinningStatistics lottoWinningStatistics = new LottoWinningStatistics(ranks, returnOfInvestment);

        outputView.writeLottoWinningStatistics(lottoWinningStatistics);
    }

    public List<Integer> readWinningNumbers() {
        List<Integer> winningNumbers = inputView.readWinningNumber();
        validateDuplicationI(winningNumbers);
        return winningNumbers;
    }

    public Integer readBonusBall(List<Integer> winningNumbers) {
        int bonusBall = inputView.readBonusBall();
        validateDuplication(bonusBall, winningNumbers);
        return bonusBall;
    }

    public void validateDuplicationI(List<Integer> winningNumber) {
        Long size = winningNumber.stream().distinct().count();
        if(size != LOTTO_SIZE) {
            throw new IllegalArgumentException("당첨 번호는 중복된 번호를 입력받을 수 없습니다.");
        }
    }

    public void validateDuplication(int bonusBall, List<Integer> winningNumber) {
        if(winningNumber.contains(bonusBall)) {
            throw new IllegalArgumentException("보너스 볼은 당첨 번호와 중복될 수 없습니다.");
        }
    }
}
