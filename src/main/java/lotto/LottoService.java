package lotto;

import java.util.List;
import java.util.stream.Collectors;
import lotto.model.LottoGenerator;
import lotto.model.LottoNumbers;
import lotto.model.Lotto;
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
        Money money = inputMoney();
        int manualCount = inputManualCount(money);
        List<LottoNumbers> manualLotto = inputManualLotto(manualCount);
        PurchaseLotto purchaseLotto = purchaseLotto(money, manualLotto);

        printPurchaseLottoInfo(manualCount, purchaseLotto);
        printIssuedLotto(manualLotto, purchaseLotto, manualCount);

        WinningNumbers winningNumbers = inputWinningNumbers();
        WinningResult winningResult = calculateWinningResult(purchaseLotto.getLotto(),
            winningNumbers);

        printStatistics(money, winningResult);
    }

    private Money inputMoney() {
        return new Money(inputView.inputMoney());
    }

    private int inputManualCount(Money money) {
        return inputView.inputManualCount(money.getAmount());
    }

    private List<LottoNumbers> inputManualLotto(int manualCount) {
        List<List<Integer>> manualNumbersInput = inputView.inputManualNumbers(manualCount);
        return manualNumbersInput.stream()
            .map(LottoNumbers::new)
            .collect(Collectors.toList());
    }

    private PurchaseLotto purchaseLotto(Money money, List<LottoNumbers> manualLotto) {
        return new PurchaseLotto(money, lottoGenerator, manualLotto);
    }

    private void printPurchaseLottoInfo(int manualCount, PurchaseLotto purchaseLotto) {
        int autoCount = purchaseLotto.purchaseCount() - manualCount;
        outputView.printPurchaseInfo(manualCount, autoCount);
    }

    private void printIssuedLotto(List<LottoNumbers> manualLotto, PurchaseLotto purchaseLotto,
        int manualCount) {
        outputView.printLotto(manualLotto);
        outputView.printLotto(
            purchaseLotto.getLotto().asList().subList(manualCount, purchaseLotto.purchaseCount())
        );
    }

    private WinningNumbers inputWinningNumbers() {
        List<Integer> winningNumbersInput = inputView.inputWinningNumber();
        int bonusBall = inputView.inputBonusBall();
        return new WinningNumbers(winningNumbersInput, bonusBall);
    }

    private WinningResult calculateWinningResult(Lotto lotto, WinningNumbers winningNumbers) {
        List<MatchCount> matchCounts = lotto.asList().stream()
            .map(lottoNumbers -> lottoNumbers.match(winningNumbers))
            .collect(Collectors.toList());
        return new WinningResult(matchCounts);
    }

    private void printStatistics(Money money, WinningResult result) {
        outputView.printWinningStatistics(money.getAmount(), result.getWinningStatistics());
    }
}
