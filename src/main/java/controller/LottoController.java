package controller;

import domain.Lotto;
import domain.LottoNumber;
import domain.LottoParser;
import domain.LottoProfitCalculator;
import domain.LottoStore;
import domain.PurchaseAmount;
import domain.WinningLotto;
import domain.WinningStatistics;
import domain.generator.RandomLottoGenerator;
import java.util.stream.Collectors;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoController {

    private final RandomLottoGenerator generator = new RandomLottoGenerator();
    private final LottoStore lottoStore = new LottoStore(generator);


    public void runLotto() {
        PurchaseAmount purchaseAmount = createPurchaseAmount();
        int manualLottoCount = readManualLottoCount(purchaseAmount);
        List<Lotto> manualLottos = readManualLottos(manualLottoCount);

        List<Lotto> purchasedLottos = lottoStore.buyLotto(purchaseAmount, manualLottos);
        printPurchasedLottos(manualLottoCount, purchasedLottos);

        WinningLotto winningLotto = createWinningLotto();
        WinningStatistics winningStatistics = new WinningStatistics(winningLotto, purchasedLottos);

        printResult(winningStatistics, purchaseAmount);
    }

    private PurchaseAmount createPurchaseAmount() {
        int amountInput = InputView.enterLottoPuchase();
        return new PurchaseAmount(amountInput);
    }

    private int readManualLottoCount(PurchaseAmount purchaseAmount) {
        int manualLottoCount = InputView.enterManualLottoCount();
        lottoStore.validateManualCount(purchaseAmount, manualLottoCount);
        return manualLottoCount;
    }

    private List<Lotto> readManualLottos(int manualLottoCount) {
        List<String> manualLottoNumbers = InputView.enterManualLottoNumbers(manualLottoCount);
        return manualLottoNumbers.stream()
                .map(LottoParser::parse)
                .collect(Collectors.toList());
    }

    private WinningLotto createWinningLotto() {
        Lotto winningLottoNumbers = LottoParser.parse(InputView.enterLottoWinningNumber());
        LottoNumber bonusBall = LottoNumber.of(InputView.enterLottoBonusBall());
        return new WinningLotto(winningLottoNumbers, bonusBall);
    }

    private void printPurchasedLottos(int manualLottoCount, List<Lotto> allLottos) {
        int autoCount = allLottos.size() - manualLottoCount;
        OutputView.printPurchasedLottoAmount(manualLottoCount, autoCount);
        OutputView.printLottoTicket(allLottos);
    }

    private void printResult(WinningStatistics stats, PurchaseAmount purchaseAmount) {
        double profitRate = LottoProfitCalculator.calculateProfit(stats, purchaseAmount.getPurchaseAmount());
        OutputView.printWinningStatistics(stats);
        OutputView.printProfitRate(profitRate);
    }
}
