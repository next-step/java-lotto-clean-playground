import static view.InputView.lottoScanner;

import domain.LottoChecker;
import domain.LottoResult;
import domain.LottoStatistics;
import domain.LottoTicketCount;
import domain.LottoTickets;
import domain.LottoWinningType;

import java.util.ArrayList;
import java.util.Map;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        LottoTicketCount lottoTicketCount = new LottoTicketCount();
        int lottoTicketTotalAmount = lottoTicketCount.convertLottoPriceToTicketCount(InputView.inputLottoTotalPrice());
        OutputView.printLottoCount(lottoTicketTotalAmount);

        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.makeLottos(lottoTicketTotalAmount);
        OutputView.printLottoNumbers(lottoTickets);

        String[] winningNumbers = InputView.inputWinningLottoNumbers().split(", ");
        String bonusNumber = InputView.inputBonusBallNumber();

        LottoChecker lottoChecker = new LottoChecker(winningNumbers, lottoTickets, bonusNumber);
        ArrayList<LottoWinningType> checkedTickets = lottoChecker.checkAllTickets();

        LottoStatistics lottoStatistics = new LottoStatistics();
        Map<LottoWinningType, Integer> countedMatches = lottoStatistics.countMatches(checkedTickets);

        OutputView.printMatchCount(countedMatches);

        LottoResult lottoResult = new LottoResult(lottoStatistics, lottoTicketTotalAmount);
        OutputView.printRateOfReturn(lottoResult.calculateProfitRate());

        InputView.closeScanner(lottoScanner);
    }
}
