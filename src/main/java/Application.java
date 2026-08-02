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

        // 1. 체크 로직을 통해 티켓들의 당첨 상태를 리스트로 반환받음
        LottoChecker lottoChecker = new LottoChecker(winningNumbers, lottoTickets, bonusNumber);
        ArrayList<LottoWinningType> checkedTickets = lottoChecker.checkAllTickets();

        // 2. 통계 객체에 넘겨 개수를 카운트함
        LottoStatistics lottoStatistics = new LottoStatistics();
        Map<LottoWinningType, Integer> countedMatches = lottoStatistics.countMatches(checkedTickets);

        OutputView.printMatchCount(countedMatches);

        // 3. 수익률 계산 및 출력
        LottoResult lottoResult = new LottoResult(lottoStatistics, lottoTicketTotalAmount);
        OutputView.printRateOfReturn(lottoResult.calculateProfitRate());
    }
}
