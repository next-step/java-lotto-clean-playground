import lotto.Lotto;
import lotto.LottoGenerator;
import lotto.LottoNumber;
import lotto.LottoResult;
import lotto.Lottos;
import lotto.PurchaseAmount;
import lotto.WinningLotto;
import view.InputView;
import view.ResultView;

public class Application {

    public static void main(String[] args) {
        try {
            execute();
        } catch (IllegalArgumentException exception) {
            ResultView.printError(exception.getMessage());
        }
    }

    private static Lottos purchaseLottos(int manualLottoCount, int automaticLottoCount) {
        Lottos manualLottos = InputView.readManualLottos(manualLottoCount);
        Lottos automaticLottos = LottoGenerator.generateLottos(automaticLottoCount);
        return manualLottos.combine(automaticLottos);
    }

    private static void processWinningResult(Lottos purchasedLottos, PurchaseAmount purchaseAmount) {
        Lotto winningNumbers = InputView.readWinningLotto();
        LottoNumber bonusNumber = InputView.readBonusNumber();
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);
        LottoResult result = purchasedLottos.createResult(winningLotto);
        ResultView.printResult(result, purchaseAmount);
    }

    private static void execute() {
        PurchaseAmount purchaseAmount = InputView.readPurchaseAmount();
        int manualLottoCount = InputView.readManualLottoCount();
        int automaticLottoCount = purchaseAmount.calculateAutomaticLottoCount(manualLottoCount);
        Lottos purchasedLottos = purchaseLottos(manualLottoCount, automaticLottoCount);

        ResultView.printLottoCount(manualLottoCount, automaticLottoCount);
        ResultView.printLottos(purchasedLottos);
        processWinningResult(purchasedLottos, purchaseAmount);
    }
}
