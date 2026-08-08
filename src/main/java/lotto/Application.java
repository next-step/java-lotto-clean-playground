package lotto;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Lottos;
import lotto.domain.PurchaseAmount;
import lotto.domain.WinningLotto;
import lotto.domain.WinningResult;
import lotto.service.LottoMachine;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final LottoMachine lottoMachine = new LottoMachine();

    public static void main(String[] args) {
        new Application().run();
    }

    private void run() {
        try {
            startLottoGame();
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception.getMessage());
        }
    }

    private void startLottoGame() {
        PurchaseAmount purchaseAmount = readPurchaseAmount();
        int manualCount = inputView.readManualCount();
        int automaticCount = purchaseAmount
                .calculateAutomaticCount(manualCount);

        Lottos lottos = purchaseLottos(
                manualCount,
                automaticCount
        );
        calculateWinningResult(lottos, purchaseAmount);
    }

    private PurchaseAmount readPurchaseAmount() {
        int amount = inputView.readPurchaseAmount();
        return new PurchaseAmount(amount);
    }

    private Lottos purchaseLottos(
            int manualCount,
            int automaticCount
    ) {
        List<Lotto> manualLottos =
                inputView.readManualLottos(manualCount);

        Lottos lottos = lottoMachine.purchase(
                automaticCount,
                manualLottos
        );

        outputView.printPurchaseResult(
                manualCount,
                automaticCount,
                lottos
        );
        return lottos;
    }

    private void calculateWinningResult(
            Lottos lottos,
            PurchaseAmount purchaseAmount
    ) {
        WinningLotto winningLotto = readWinningLotto();
        WinningResult result =
                lottos.calculateResult(winningLotto);

        double profitRate =
                result.calculateProfitRate(purchaseAmount);

        outputView.printStatistics(result, profitRate);
    }

    private WinningLotto readWinningLotto() {
        Lotto winningNumbers = inputView.readWinningLotto();
        int bonusNumber = inputView.readBonusNumber();

        return new WinningLotto(
                winningNumbers,
                new LottoNumber(bonusNumber)
        );
    }
}
