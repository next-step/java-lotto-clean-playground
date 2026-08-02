import domain.LottoResult;
import domain.WinningRate;
import view.InputView;
import view.ResultView;
import domain.Lotto;

import java.util.List;


public class Application {
    public static void main(String[] args) {
        Lotto lotto = new Lotto();
        LottoResult lottoResult = new LottoResult();
        WinningRate winningRate = new WinningRate();

        int purchasePrice = InputView.inputPrice();
        int lottoCount = lotto.calculateCount(purchasePrice);

        List<List<Integer>> lottos = lotto.lottoLists(lottoCount);
        ResultView.printPurchase(lottos);

        List<Integer> wins = InputView.inputWinning();
        int bonusBall = InputView.inputBonusBall();
        List<Integer> counts = lottoResult.calculateCounts(lottos, wins, bonusBall);

        int winPrice = winningRate.calculateWinPrice(counts);
        double rate = winningRate.calculateRate(winPrice, purchasePrice);

        ResultView.printResult(counts, rate);
    }
}

