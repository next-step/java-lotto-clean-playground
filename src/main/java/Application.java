import domain.Lotto;
import domain.LottoMachine;
import domain.LottoResult;
import domain.Lottos;
import domain.PurchaseAmount;
import domain.Rank;
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
        LottoMachine lottoMachine = new LottoMachine(lottoGenerator());

        List<Lotto> lottoList = lottoMachine.purchase(purchaseAmount.getLottosCount());
        Lottos lottos = new Lottos(lottoList);

        ResultView.printLottoResult(lottoList);

        Lotto winnerNumbers = Lotto.from(InputView.readWinnerNumber(sc));
        Map<Rank, Integer> rankCount = lottos.getRankCount(winnerNumbers);

        LottoResult result = new LottoResult(rankCount);
        ResultView.printStats(result, purchaseAmount.getLottosCount());


    }

    private static LottoGenerator lottoGenerator() {
        return new RandomLottoGenerator();
    }
}
