import static view.InputView.lottoScanner;

import domain.LottoChecker;
import domain.LottoResult;
import domain.LottoStatistics;
import domain.LottoTicketCount;
import domain.LottoTickets;
import domain.LottoWinningType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        LottoTicketCount lottoTicketCount = new LottoTicketCount();
        int totalCount = lottoTicketCount.convertLottoPriceToTicketCount(InputView.inputLottoTotalPrice());

        int manualCount = InputView.inputUserSelectedLottoCount();
        List<String> userSelectedNumbersInput = InputView.inputUserSelectedLottoNumbers(manualCount);

        int autoCount = totalCount - manualCount;

        OutputView.printLottoCount(manualCount, autoCount);

        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.addUserSelectedLottos(userSelectedNumbersInput);
        lottoTickets.addAutoLottos(autoCount);

        OutputView.printLottoNumbers(lottoTickets);

        String[] winningNumbers = InputView.inputWinningLottoNumbers().split(",\\s*");
        String bonusNumber = InputView.inputBonusBallNumber();

        LottoChecker lottoChecker = new LottoChecker(winningNumbers, lottoTickets, bonusNumber);
        ArrayList<LottoWinningType> checkedTickets = lottoChecker.checkAllTickets();

        LottoStatistics lottoStatistics = new LottoStatistics();
        Map<LottoWinningType, Integer> countedMatches = lottoStatistics.countMatches(checkedTickets);

        OutputView.printMatchCount(countedMatches);

        LottoResult lottoResult = new LottoResult(lottoStatistics, (totalCount * LottoTicketCount.PRICE_PER_ONE_LOTTO_TICKET));
        OutputView.printRateOfReturn(lottoResult.calculateProfitRate());

        InputView.closeScanner(lottoScanner);
    }
}
