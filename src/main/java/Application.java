import domain.LottoChecker;
import domain.LottoResult;
import domain.LottoTicketCount;
import domain.LottoTickets;
import java.util.Map;
import java.util.Scanner;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        LottoTicketCount lottoTicketCount = new LottoTicketCount();
        int lottoTicketTotalAmount;
        lottoTicketTotalAmount = lottoTicketCount.convertLottoPriceToTicketCount(InputView.inputLottoTotalPrice());
        OutputView.printLottoCount(lottoTicketTotalAmount);

        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.makeLottos(lottoTicketTotalAmount);
        OutputView.printLottoNumbers(lottoTickets);

        String[] winningNumbers  = InputView.inputWinningLottoNumbers().split(", ");
        // todo: 사용자가 입력한 지난주 당첨번호가 6개가 아니라면,, -> 예외처리? 어디에서?

        LottoChecker lottoChecker = new LottoChecker(winningNumbers, lottoTickets);
        Map<Integer,Integer> countedMatches = lottoChecker.countMatches(lottoChecker.checkAllTickets());
        OutputView.printMatchCount(countedMatches);

        LottoResult lottoResult = new LottoResult(countedMatches, lottoTicketTotalAmount);

        OutputView.printRateOfReturn(lottoResult.calculateProfitRate());



    }
}
