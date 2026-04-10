package controller;

import domain.BonusBall;
import domain.Lotto;
import domain.LottoNumber;
import domain.LottoShop;
import domain.Lottos;
import domain.ManualLottoCount;
import domain.PurchaseAmount;
import domain.WinningLotto;
import domain.WinningStatistics;
import dto.WinningResult;
import view.InputView;
import view.OutputView;

import java.util.function.Supplier;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoShop lottoShop;

    public LottoController(InputView inputView, OutputView outputView, LottoShop lottoShop) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoShop = lottoShop;
    }

    public void run() {
        PurchaseAmount purchaseAmount = retryUntilValid(() -> new PurchaseAmount(inputView.readAmount()));
        ManualLottoCount manualLottoCount = retryUntilValid(() -> new ManualLottoCount(inputView.readManualCount(), purchaseAmount));
        Lottos lottos = retryUntilValid(() -> purchaseLottos(purchaseAmount, manualLottoCount));

        printPurchaseResult(lottos, manualLottoCount);

        WinningLotto winningLotto = readWinningLotto();
        WinningStatistics winningStatistics = WinningStatistics.from(lottos, winningLotto);

        printWinningResult(winningStatistics, purchaseAmount);
    }

    private Lottos purchaseLottos(PurchaseAmount purchaseAmount, ManualLottoCount manualLottoCount) {
        Lottos manualLottos = Lottos.from(inputView.readManualNumbers(manualLottoCount.count()));
        return lottoShop.purchase(purchaseAmount, manualLottos);
    }

    private void printPurchaseResult(Lottos lottos, ManualLottoCount manualLottoCount) {
        int autoCount = lottos.size() - manualLottoCount.count();
        outputView.printResultHeader(manualLottoCount.count(), autoCount);
        outputView.printLottos(lottos.toNumberLists());
    }

    private WinningLotto readWinningLotto() {
        Lotto winningLotto = retryUntilValid(() -> Lotto.from(inputView.readWinningNumbers()));
        return retryUntilValid(() -> {
            BonusBall bonusBall = new BonusBall(new LottoNumber(inputView.readBonusBall()));
            return new WinningLotto(winningLotto, bonusBall);
        });
    }

    private void printWinningResult(WinningStatistics winningStatistics, PurchaseAmount purchaseAmount) {
        outputView.printWinningStatistics(WinningResult.from(winningStatistics));
        outputView.printProfitRate(winningStatistics.calculateProfitRate(purchaseAmount));
    }

    private <T> T retryUntilValid(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
