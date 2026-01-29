package controller;

import domain.lotto.Lotto;
import domain.lotto.LottoNumber;
import domain.lotto.LottoNumberGenerator;
import domain.lotto.LottoNumbers;
import domain.lotto.LottoResult;
import domain.lotto.Lottos;
import domain.lotto.Rank;
import domain.lotto.WinningLotto;
import domain.purchase.LottoFactory;
import domain.purchase.Money;
import domain.purchase.PurchaseCalculator;
import view.InputView;
import view.OutputView;
import java.util.List;

public class LottoVendingMachine {

    private final InputView inputView;
    private final OutputView outputView;
    private final PurchaseCalculator purchaseCalculator;
    private final LottoFactory lottoFactory;

    public LottoVendingMachine(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.purchaseCalculator = new PurchaseCalculator();
        this.lottoFactory = new LottoFactory(new LottoNumberGenerator());
    }

    public void run() {
        int purchaseAmount = inputPurchaseAmount();
        int totalLottoCount = purchaseCalculator.calculateLottoCount(Money.won(purchaseAmount));

        int manualCount = inputManualLottoCount();
        List<String> manualNumbers = inputManualLottoNumbers(manualCount);

        int autoCount = totalLottoCount - manualCount;
        Lottos lottos = lottoFactory.createLottos(manualNumbers, autoCount);
        outputView.printPurchasedLottos(manualCount, autoCount, lottos);

        WinningLotto winningLotto = inputWinningLotto();
        LottoResult result = calculateResult(lottos, winningLotto);
        outputView.printResult(result, purchaseAmount);
    }

    private int inputPurchaseAmount() {
        outputView.printLottoPurchaseAmount();
        return inputView.readLottoPurchaseAmount();
    }

    private int inputManualLottoCount() {
        outputView.printManualLottoCount();
        return inputView.readManualLottoCount();
    }

    private List<String> inputManualLottoNumbers(int count) {
        outputView.printManualLottoNumbers();
        return inputView.readManualLottoNumbers(count);
    }

    private WinningLotto inputWinningLotto() {
        outputView.printWinningNumbersInput();
        String winningNumbers = inputView.readWinningNumbers();
        Lotto winningLotto = new Lotto(LottoNumbers.from(winningNumbers));

        outputView.printBonusNumberInput();
        int bonusNumber = inputView.readBonusNumber();
        LottoNumber bonus = new LottoNumber(bonusNumber);

        return new WinningLotto(winningLotto, bonus);
    }

    private LottoResult calculateResult(Lottos lottos, WinningLotto winningLotto) {
        LottoResult result = new LottoResult();
        for (Lotto lotto : lottos.getLottos()) {
            Rank rank = winningLotto.match(lotto);
            result.addRank(rank);
        }
        return result;
    }
}
