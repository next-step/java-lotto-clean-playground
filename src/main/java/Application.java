import domain.Lotto;
import domain.LottoMachine;
import domain.LottoResult;
import domain.Lottos;
import domain.PurchaseAmount;
import domain.Rank;
import domain.WinningLotto;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import lottoGenerator.LottoGenerator;
import lottoGenerator.RandomLottoGenerator;
import view.InputView;
import view.ResultView;

public class Application {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        PurchaseAmount purchaseAmount = new PurchaseAmount(InputView.readPrice(sc));
        int manualSelectionCount = InputView.readManualSelectionCount(sc);
        LottoMachine lottoMachine = new LottoMachine(lottoGenerator());

        for (int i = 0; i < manualSelectionCount; i++) {
            lottoMachine.manualSelection(InputView.readManualSelection(sc));
        }
        List<Lotto> lottoList = lottoMachine.purchase(purchaseAmount.getLottosCount() - manualSelectionCount);
        Lottos lottos = new Lottos(lottoList);

        ResultView.printLottoResult(lottoList);

        WinningLotto winningLotto = new WinningLotto(InputView.readWinnerNumber(sc), InputView.readBonusNumber(sc));
        Map<Rank, Integer> rankCount = lottos.getRankCount(winningLotto);

        LottoResult result = new LottoResult(rankCount);
        ResultView.printStats(result, purchaseAmount.getLottosCount());

    }

    private static LottoGenerator lottoGenerator() {
        return new RandomLottoGenerator();
    }
}
