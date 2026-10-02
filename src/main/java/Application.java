import domain.Lotto;
import domain.LottoMachine;
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
        List<Lotto> lottos = purchaseLottos(scanner, purchaseAmount.getLottosCount());
        printWinningResult(scanner, new Lottos(lottos), purchaseAmount.getLottosCount());
    }

    private static List<Lotto> purchaseLottos(Scanner scanner, int totalCount) {
        int manualCount = InputView.readManualCount(scanner, totalCount);
        List<Lotto> manualLottos = InputView.readManualLottos(scanner, manualCount);
        LottoMachine machine = new LottoMachine(new RandomLottoNumberGenerator());
        List<Lotto> lottos = machine.purchase(totalCount, manualLottos);
        ResultView.printLottoResult(lottos, manualCount);
        return lottos;
    }

    private static void printWinningResult(Scanner scanner, Lottos lottos, int totalCount) {
        Lotto winningLotto = new Lotto(InputView.readWinnerNumber(scanner));
        int bonusNumber = InputView.readBonusNumber(scanner);
        LottoResult result = lottos.calculateResult(winningLotto, bonusNumber);
        ResultView.printStats(result, totalCount);
    }
}
