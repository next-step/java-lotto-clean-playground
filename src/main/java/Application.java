import domain.*;

import java.util.List;
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

        Lotto winnerNumbers = new Lotto(InputView.readWinnerNumber(sc));
        List<Integer> matchCount = lottos.getMatchCount(winnerNumbers);

        LottoResult result = new LottoResult(matchCount);
        ResultView.printStats(result, purchaseAmount.getLottosCount());


    }
    private static LottoGenerator lottoGenerator(){
        return new RandomLottoGenerator();
    }
}
