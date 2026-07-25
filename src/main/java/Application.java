import domain.BonusBall;
import domain.Lotto;
import domain.LottoGenerator;
import domain.Lottos;
import domain.PurchaseAmount;
import domain.WinningLottoNumber;
import domain.WinningStatistics;
import java.util.List;
import view.InputView;
import view.ResultView;

public class Application {
    public static void main(String[] args) {
        int amount = InputView.getLottoPurchaseAmount();
        PurchaseAmount purchaseAmount = new PurchaseAmount(amount);

        int manualCount = InputView.getManualCount();
        int autoCount = purchaseAmount.calculateAutoCount(manualCount);

        List<List<Integer>> manualNumbers = InputView.getManualNumbers(manualCount);

        Lottos lottos = new Lottos();

        for (List<Integer> numbers : manualNumbers) {
            Lotto lotto = new Lotto(numbers);
            lottos.add(lotto);
        }

        LottoGenerator lottoGenerator = new LottoGenerator();
        for (int i = 0; i < autoCount; i++) {
            Lotto lotto = lottoGenerator.generateLotto();
            lottos.add(lotto);
        }

        ResultView.printPurchaseAmount(manualCount, autoCount);
        for (Lotto lotto : lottos.getLottos()) {
            ResultView.printLotto(lotto);
        }

        List<Integer> winningNumbersInput = InputView.getWinningNumbers();
        WinningLottoNumber winningLottoNumber = new WinningLottoNumber(winningNumbersInput);

        int bonusNumber = InputView.getBonusBall();
        BonusBall bonusBall = new BonusBall(bonusNumber, winningLottoNumber.getWinningNumbers());

        WinningStatistics statistics = new WinningStatistics(lottos, winningLottoNumber, bonusBall);
        ResultView.printStatistics(statistics);

        double profitRate = statistics.calculateProfitRate(amount);
        ResultView.printProfitRate(profitRate);
    }
}
