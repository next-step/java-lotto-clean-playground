import domain.common.Money;
import domain.lotto.AutoLottoGenerator;
import domain.lotto.LottoResult;
import domain.lotto.LottoStore;
import domain.lotto.LottoTicket;
import java.util.List;
import view.InputView;
import view.OutputView;

public class LottoApplication {

    public void start() {
        final Money money = InputView.inputMoney();

        LottoStore lottoStore = new LottoStore(new AutoLottoGenerator());

        List<LottoTicket> lottoTickets = lottoStore.sellLottos(money);

        OutputView.printResult(lottoTickets);

        final LottoResult lottoResult = InputView.inputWinningNumbers();

        OutputView.printStatistics(money, lottoResult, lottoTickets);
    }

    public static void main(String[] args) {
        LottoApplication lottoApplication = new LottoApplication();
        lottoApplication.start();
    }
}
