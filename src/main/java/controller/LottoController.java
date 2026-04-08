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

import java.util.List;

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
        PurchaseAmount purchaseAmount = new PurchaseAmount(inputView.readAmount());
        ManualLottoCount manualLottoCount = new ManualLottoCount(inputView.readManualCount(), purchaseAmount);
        Lottos lottos = purchaseLottos(purchaseAmount, manualLottoCount);

        printPurchaseResult(lottos, manualLottoCount);

        WinningLotto winningLotto = readWinningLotto();
        WinningStatistics winningStatistics = WinningStatistics.from(lottos, winningLotto);

        printWinningResult(winningStatistics, purchaseAmount);
    }

    private Lottos purchaseLottos(PurchaseAmount purchaseAmount, ManualLottoCount manualLottoCount) {
        Lottos manualLottos = toLottos(inputView.readManualNumbers(manualLottoCount.count()));
        return lottoShop.purchase(purchaseAmount, manualLottos);
    }

    private void printPurchaseResult(Lottos lottos, ManualLottoCount manualLottoCount) {
        int autoCount = lottos.size() - manualLottoCount.count();
        outputView.printResultHeader(manualLottoCount.count(), autoCount);
        outputView.printLottos(lottos.toNumberLists());
    }

    private WinningLotto readWinningLotto() {
        List<Integer> winningNumbers = inputView.readWinningNumbers();
        BonusBall bonusBall = new BonusBall(new LottoNumber(inputView.readBonusBall()));
        return new WinningLotto(toLotto(winningNumbers), bonusBall);
    }

    private void printWinningResult(WinningStatistics winningStatistics, PurchaseAmount purchaseAmount) {
        outputView.printWinningStatistics(WinningResult.from(winningStatistics));
        outputView.printProfitRate(winningStatistics.calculateProfitRate(purchaseAmount));
    }

    private Lottos toLottos(List<List<Integer>> numbers) {
        return new Lottos(numbers.stream()
                .map(this::toLotto)
                .toList());
    }

    private Lotto toLotto(List<Integer> numbers) {
        return new Lotto(numbers.stream()
                .map(LottoNumber::new)
                .toList());
    }
}
