package lotto;

import java.util.List;
import java.util.stream.Collectors;
import lotto.model.LottoGenerator;
import lotto.model.Lottos;
import lotto.model.MatchCount;
import lotto.model.Money;
import lotto.model.PurchaseLotto;
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
        Money money = new Money(inputView.inputMoney());
        PurchaseLotto purchaseLotto = new PurchaseLotto(money, lottoGenerator);
        Lottos lottos = purchaseLotto.getLottos();

        outputView.printPurchasedLottoCount(purchaseLotto.purchaseCount());
        outputView.printLottos(lottos.asList());

        WinningNumbers winningNumbers = new WinningNumbers(inputView.inputWinningNumber());
        WinningResult winningResult = calculateWinningResult(lottos, winningNumbers);

        outputView.printWinningStatistics(money.getAmount(), winningResult.getWinningStatistics());
    }

    private WinningResult calculateWinningResult(Lottos lottos, WinningNumbers winningNumbers) {
        List<MatchCount> matchCounts = lottos.asList().stream()
            .map(lotto -> lotto.match(winningNumbers))
            .collect(Collectors.toList());

        return new WinningResult(matchCounts);
    }
}
