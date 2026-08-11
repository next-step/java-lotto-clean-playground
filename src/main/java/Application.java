import static view.InputView.lottoScanner;

import domain.Lotto;
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
        int totalCount = LottoTicketCount.convertLottoPriceToTicketCount(InputView.inputLottoTotalPrice());

        int manualCount = InputView.inputUserSelectedLottoCount(totalCount);
        List<Lotto> userSelectedLottos = inputUserSelectedLottos(manualCount);

        int autoCount = totalCount - manualCount;

        OutputView.printLottoCount(manualCount, autoCount);

        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.addUserSelectedLottos(userSelectedLottos);
        lottoTickets.addAutoLottos(autoCount);

        OutputView.printLottoNumbers(lottoTickets);

        Lotto winningLotto = inputWinningLotto();
        String bonusNumber = InputView.inputBonusBallNumber();

        LottoChecker lottoChecker = new LottoChecker(winningLotto, lottoTickets, bonusNumber);
        ArrayList<LottoWinningType> checkedTickets = lottoChecker.checkAllTickets();

        LottoStatistics lottoStatistics = new LottoStatistics();
        Map<LottoWinningType, Integer> countedMatches = lottoStatistics.countMatches(checkedTickets);

        OutputView.printMatchCount(countedMatches);

        LottoResult lottoResult = new LottoResult(lottoStatistics, (totalCount * LottoTicketCount.PRICE_PER_ONE_LOTTO_TICKET));
        OutputView.printRateOfReturn(lottoResult.calculateProfitRate());

        InputView.closeScanner(lottoScanner);
    }

    private static List<Lotto> inputUserSelectedLottos(int manualCount) {
        InputView.printUserSelectedLottoNumbersPrompt();
        List<Lotto> userSelectedLottos = new ArrayList<>();
        for (int i = 0; i < manualCount; i++) {
            userSelectedLottos.add(readLottoWithRetry("로또 번호는 숫자로만 구성되어야 합니다."));
        }
        return userSelectedLottos;
    }

    private static Lotto inputWinningLotto() {
        InputView.printWinningLottoNumbersPrompt();
        return readLottoWithRetry("당첨 번호는 숫자로만 구성되어야 합니다.");
    }

    private static Lotto readLottoWithRetry(String numberFormatErrorMessage) {
        while (true) {
            try {
                return new Lotto(InputView.readLottoNumbers());
            } catch (NumberFormatException e) {
                OutputView.printError(numberFormatErrorMessage);
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }
}
