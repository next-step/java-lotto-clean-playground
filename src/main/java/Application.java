import domain.Lotto;
import domain.LottoGenerator;
import domain.LottoResult;
import domain.LottoNumber;
import domain.PurchaseCount;
import domain.PurchaseAmount;
import domain.Rank;
import domain.WinningLotto;
import domain.WinningRate;


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
        int manualCount = InputView.inputManualCount();

        PurchaseCount purchaseCount = PurchaseCount.from(lottoCount, manualCount);
        int autoCount = purchaseCount.calculateAutoCount();

        List<Lotto> manualLotto = InputView.inputManualLotto(manualCount);
        List<Lotto> autoLotto = lottoGenerator.lottoLists(autoCount);

        manualLotto.addAll(autoLotto);
        ResultView.printPurchase(manualLotto, manualCount, autoCount);

        Lotto wins = InputView.inputWinning();
        LottoNumber bonusBall = InputView.inputBonusBall();

        WinningLotto winningLotto = WinningLotto.from(wins, bonusBall);
        List<Rank> ranks = lottoResult.calculateRanks(manualLotto, winningLotto);

        long winPrice = winningRate.calculateWinPrice(ranks);
        double rate = winningRate.calculateRate(winPrice, purchaseAmount);

        List<Integer> rankCounts = lottoResult.countRanks(ranks);
        ResultView.printResult(rankCounts, rate);
    }
}

