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
        PurchaseAmount purchaseAmount = PurchaseAmount.from(purchasePrice);

        int lottoCount = purchaseAmount.calculateCount();
        int passiveCount = InputView.inputPassiveCount();
        int autoCount = lottoCount - passiveCount;

        List<Lotto> passiveLotto = InputView.inputPassiveLotto(passiveCount);
        List<Lotto> autoLotto = lottoGenerator.lottoLists(autoCount);

        passiveLotto.addAll(autoLotto);
        ResultView.printPurchase(passiveLotto, passiveCount, autoCount);

        List<Integer> wins = InputView.inputWinning();
        int bonusBall = InputView.inputBonusBall();
        List<Rank> ranks = lottoResult.calculateRanks(passiveLotto, wins, bonusBall);

        int winPrice = winningRate.calculateWinPrice(ranks);
        double rate = winningRate.calculateRate(winPrice, purchasePrice);

        ResultView.printResult(ranks, rate);
    }
}

