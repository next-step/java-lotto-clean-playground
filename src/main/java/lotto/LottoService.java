package lotto;

import java.util.List;
import java.util.stream.Collectors;
import lotto.model.Lotto;
import lotto.model.LottoGenerator;
import lotto.model.LottoTicket;
import lotto.model.MatchCount;
import lotto.model.Money;
import lotto.model.WinningNumbers;
import lotto.model.WinningResult;
import lotto.view.LottoInputView;
import lotto.view.LottoOutputView;

public class LottoService {

    private final LottoInputView inputView;
    private final LottoOutputView outputView;
    private final LottoGenerator lottoGenerator;

    public LottoService(LottoInputView inputView, LottoOutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoGenerator = new LottoGenerator();
    }

    public void start() {
        Money purchaseMoney = new Money(inputView.inputMoney());

        List<Lotto> lottos = purchaseLottos(purchaseMoney);

        LottoTicket lottoTicket = purchaseMoney.calculateTicketCount();

        outputView.printPurchasedLottoCount(lottoTicket.getCount());
        outputView.printLottos(lottos);

        WinningNumbers winningNumbers = new WinningNumbers(inputView.inputWinningNumber());

        WinningResult winningResult = calculateWinningResult(lottos, winningNumbers);

        outputView.printWinningStatistics(purchaseMoney.getValue(),
            winningResult.getWinningStatistics());
    }

    private List<Lotto> purchaseLottos(Money money) {
        return lottoGenerator.generate(money.calculateTicketCount().getCount());
    }

    private WinningResult calculateWinningResult(List<Lotto> lottos, WinningNumbers winningNumbers) {
        List<MatchCount> matchCounts = lottos.stream()
            .map(lotto -> lotto.matchWith(winningNumbers))
            .collect(Collectors.toList());

        return new WinningResult(matchCounts);
    }
}
