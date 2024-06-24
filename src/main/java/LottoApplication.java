import domain.lotto.AutoLottoGenerator;
import domain.lotto.LottoStore;
import domain.lotto.LottoTicket;
import domain.lotto.Money;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoApplication {
    
    public void start() {
        final Money money = InputView.inputMoney();

        LottoStore lottoStore = new LottoStore(new AutoLottoGenerator());

        List<LottoTicket> lottoTickets = lottoStore.sellLottos(money);

        OutputView.printResult(lottoTickets);
    }

    public static void main(String[] args) {
        LottoApplication lottoApplication = new LottoApplication();
        lottoApplication.start();
    }
}
