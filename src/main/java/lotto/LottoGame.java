package lotto;

import java.util.List;
import lotto.model.Lotto;
import lotto.model.LottoGenerator;
import lotto.model.LottoNumbers;
import lotto.model.Money;
import lotto.model.PurchaseResult;
import lotto.model.WinningNumbers;
import lotto.model.WinningResult;
import lotto.view.LottoInputView;
import lotto.view.LottoOutputView;

public class LottoGame {

    private final LottoInputView inputView;
    private final LottoOutputView outputView;
    private final LottoGenerator lottoGenerator;

    public LottoGame(LottoGenerator lottoGenerator
        , LottoInputView inputView
        , LottoOutputView outputView
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoGenerator = lottoGenerator;
    }

    public void play() {
        PurchaseResult purchaseResult = purchase();
        WinningResult winningResult = checkWinning(purchaseResult);
        displayResults(purchaseResult, winningResult);
    }

    private PurchaseResult purchase() {
        Money purchaseAmount = inputView.inputMoney();
        int manualCount = inputView.inputManualCount(purchaseAmount.getAmount());
        List<LottoNumbers> manualNumbers = inputView.inputManualNumbers(manualCount);

        Lotto manualLotto = new Lotto(manualNumbers);
        Lotto autoLotto = generateAutoLotto(purchaseAmount, manualCount);
        Lotto allLotto = new Lotto(lottoGenerator.mergeLottoNumbers(manualLotto, autoLotto));

        return new PurchaseResult(manualLotto, autoLotto, allLotto, purchaseAmount);
    }

    private Lotto generateAutoLotto(Money purchaseAmount, int manualCount) {
        int autoCount =
            (int) (purchaseAmount.getAmount() - manualCount * Lotto.PRICE) / Lotto.PRICE;
        List<LottoNumbers> autoNumbers = lottoGenerator.generateAutoNumbers(autoCount);
        return new Lotto(autoNumbers);
    }

    private WinningResult checkWinning(PurchaseResult purchaseResult) {
        WinningNumbers winningNumbers = inputWinningNumbers();
        return purchaseResult.getAllLotto().checkWinning(winningNumbers);
    }

    private void displayResults(PurchaseResult purchaseResult, WinningResult winningResult) {
        outputView.printPurchaseInfo(purchaseResult.getManualCount(),
            purchaseResult.getAutoCount());
        outputView.printLotto(purchaseResult.getManualLotto().getNumbers());
        outputView.printLotto(purchaseResult.getAutoLotto().getNumbers());
        outputView.printWinningStatistics(winningResult, purchaseResult.getPurchaseAmount());
    }

    private WinningNumbers inputWinningNumbers() {
        return new WinningNumbers(
            inputView.inputWinningNumber(),
            inputView.inputBonusBall()
        );
    }
}
