import domain.common.Money;
import domain.lotto.LottoNumber;
import domain.lotto.LottoPurchasePrice;
import domain.lotto.LottoResult;
import domain.lotto.LottoTicket;
import domain.lotto.LottoTickets;
import domain.lotto.ManualCount;
import domain.lotto.dto.StatistsDto;
import java.util.List;
import view.InputView;
import view.OutputView;

public class LottoApplication {

    public void start() {
        final Money money = InputView.inputMoney();

        final LottoPurchasePrice lottoPurchasePrice = new LottoPurchasePrice(money);

        final ManualCount manualCount = new ManualCount(lottoPurchasePrice.getCount(), InputView.inputManualCount());
        final List<String[]> manualLottoNumbers = InputView.inputManualLotto(manualCount.getCount());

        LottoTickets manualLottoTickets = LottoTickets.createManualLottoTicket(manualLottoNumbers);
        LottoTickets autoLottoTickets = LottoTickets.createAutoLottoTicket(lottoPurchasePrice, manualCount);

        LottoTickets mergedLottoTickets = manualLottoTickets.addAll(autoLottoTickets);

        OutputView.printResult(
                manualCount.getCount(), 
                lottoPurchasePrice.getCount() - manualCount.getCount(), 
                mergedLottoTickets.getLottoTickets()
        );

        final LottoTicket winningTicket = InputView.inputWinningNumbers();
        final LottoNumber bonusNumber = InputView.inputBonusNumber();

        final LottoResult lottoResult = new LottoResult(winningTicket, bonusNumber);

        StatistsDto statistsDto = lottoResult.makeStatistics(money, mergedLottoTickets.getLottoTickets());

        OutputView.printStatistics(statistsDto);
    }

    public static void main(String[] args) {
        LottoApplication lottoApplication = new LottoApplication();
        lottoApplication.start();
    }
}
