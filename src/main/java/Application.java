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

        PurchaseCount purchaseCount = PurchaseCount.from(lottoCount, passiveCount);
        int autoCount = purchaseCount.calculateAutoCount();

        List<Lotto> passiveLotto = InputView.inputPassiveLotto(passiveCount);
        List<Lotto> autoLotto = lottoGenerator.lottoLists(autoCount);

        passiveLotto.addAll(autoLotto);
        ResultView.printPurchase(passiveLotto, passiveCount, autoCount);

        Lotto wins = InputView.inputWinning();
        LottoNumber bonusBall = InputView.inputBonusBall();

        WinningLotto winningLotto = WinningLotto.from(wins, bonusBall);
        List<Rank> ranks = lottoResult.calculateRanks(passiveLotto, winningLotto);

        long winPrice = winningRate.calculateWinPrice(ranks);
        double rate = winningRate.calculateRate(winPrice, purchaseAmount);

        List<Integer> rankCounts = lottoResult.countRanks(ranks);
        ResultView.printResult(rankCounts, rate);
    }
}

