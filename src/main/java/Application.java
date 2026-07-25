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
        int count = purchaseAmount.calculateLottoCount();

        LottoGenerator lottoGenerator = new LottoGenerator();
        ResultView.printPurchaseAmount(count);

        Lottos lottos = new Lottos();
        for (int i = 0; i < count; i++) {
            Lotto lotto = lottoGenerator.generateLotto();
            lottos.add(lotto);
            ResultView.printLotto(lotto);
        }

        List<Integer> winningNumbersInput = InputView.getWinningNumbers();
        WinningLottoNumber winningLottoNumber = new WinningLottoNumber(winningNumbersInput);

        WinningStatistics statistics = new WinningStatistics(lottos, winningLottoNumber);
        ResultView.printStatistics(statistics);
    }
}
