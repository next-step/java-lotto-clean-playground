import domain.LottoNumber;
import domain.LottoStatistics;
import domain.Lottos;
import domain.PurchaseAmount;
import domain.WinningLotto;
import domain.WinningNumbers;

import view.InputView;
import view.OutputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        PurchaseAmount purchaseAmount = new PurchaseAmount(inputView.readPurchaseAmount());

        Lottos lottos = purchaseLottos(inputView, outputView, purchaseAmount);

        WinningLotto winningLotto = createWinningLotto(inputView);

        printStatistics(outputView, lottos, winningLotto, purchaseAmount);
    }

    private static Lottos purchaseLottos(InputView inputView, OutputView outputView, PurchaseAmount purchaseAmount) {
        int manualLottoCount = inputView.readManualLottoCount();
        int autoLottoCount = purchaseAmount.calculateAutoLottoCount(manualLottoCount);

        List<List<Integer>> manualLottoNumbers = inputView.readManualLottoNumbers(manualLottoCount);

        Lottos lottos = new Lottos(manualLottoNumbers, autoLottoCount);

        outputView.printPurchasedCount(manualLottoCount, autoLottoCount);
        outputView.printLottos(lottos);

        return lottos;
    }

    private static WinningLotto createWinningLotto(InputView inputView) {
        WinningNumbers winningNumbers = new WinningNumbers(inputView.readWinningNumbers());
        LottoNumber bonusNumber = new LottoNumber(inputView.readBonusNumber());

        return new WinningLotto(winningNumbers, bonusNumber);
    }

    private static void printStatistics(OutputView outputView, Lottos lottos,
                                        WinningLotto winningLotto, PurchaseAmount purchaseAmount) {
        LottoStatistics lottoStatistics = new LottoStatistics(lottos, winningLotto);
        outputView.printLottoStatistics(purchaseAmount, lottoStatistics);
    }
}
