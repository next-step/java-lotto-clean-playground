import domain.*;

import java.util.List;
import java.util.Scanner;

import view.InputView;
import view.ResultView;

public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PurchaseAmount purchaseAmount = new PurchaseAmount(InputView.readPrice(scanner));
        Lottos lottos = purchaseLottos(scanner, purchaseAmount.getLottosCount());
        showWinningResult(scanner, lottos, purchaseAmount);
    }

    private static Lottos purchaseLottos(Scanner scanner, int totalCount) {
        int manualCount = InputView.readManualCount(scanner, totalCount);
        List<Lotto> manualLottos = InputView.readManualLottos(scanner, manualCount);
        LottoMachine machine = new LottoMachine(new RandomLottoNumberGenerator());
        Lottos lottos = machine.purchase(totalCount, manualLottos);
        ResultView.printLottoResult(lottos, manualCount);
        return lottos;
    }

    private static void showWinningResult(Scanner scanner, Lottos lottos, PurchaseAmount purchaseAmount) {
        Lotto winningNumbers = new Lotto(
                InputView.readWinningNumbers(scanner));
        LottoNumber bonusNumber = InputView.readBonusNumber(scanner);

        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        LottoResult result = lottos.calculateResult(winningLotto);
        ResultView.printStats(result, purchaseAmount);
    }
}
