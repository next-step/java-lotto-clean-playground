import domain.*;
import view.InputView;
import view.ResultView;

import java.util.List;


public class Application {
    public static void main(String[] args) {
        LottoGenerator lottoGenerator = new LottoGenerator();
        LottoResult lottoResult = new LottoResult();
        WinningRate winningRate = new WinningRate();

        int purchasePrice = InputView.inputPrice();
        int lottoCount = lottoGenerator.calculateCount(purchasePrice);
        int passiveCount = InputView.inputPassiveCount();
        int autoCount = lottoCount - passiveCount;

        List<List<LottoNumber>> passiveLotto = InputView.inputPassiveLotto(passiveCount);
        List<List<LottoNumber>> autoLotto = lottoGenerator.lottoLists(autoCount);

        passiveLotto.addAll(autoLotto);
        ResultView.printPurchase(passiveLotto, passiveCount, autoCount);
        
        List<Integer> wins = InputView.inputWinning();
        int bonusBall = InputView.inputBonusBall();
        List<Integer> counts = lottoResult.calculateCounts(passiveLotto, wins, bonusBall);

        int winPrice = winningRate.calculateWinPrice(counts);
        double rate = winningRate.calculateRate(winPrice, purchasePrice);

        ResultView.printResult(counts, rate);
    }
}

