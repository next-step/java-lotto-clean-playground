import domain.Lotto;
import domain.LottoMachine;
import domain.LottoNumber;
import domain.LottoResult;
import domain.Lottos;
import domain.PurchaseAmount;
import domain.RandomLottoNumberGenerator;
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
        Lotto winningLotto = new Lotto(InputView.readWinningNumbers(scanner));
        LottoNumber bonusNumber = InputView.readBonusNumber(scanner);
        LottoResult result = lottos.calculateResult(winningLotto, bonusNumber);
        ResultView.printStats(result, purchaseAmount);
    }
}
