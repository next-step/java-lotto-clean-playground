package controller;

import domain.Lotto;
import domain.LottoMachine;
import domain.LottoResult;
import domain.Lottos;
import domain.PurchaseAmount;
import domain.Rank;
import domain.WinningLotto;
import java.util.List;
import java.util.Map;
import lottoGenerator.LottoGenerator;
import lottoGenerator.RandomLottoGenerator;
import view.InputView;
import view.ResultView;

public class Controller {

    public void run() {
        int price = InputView.readPrice();
        int manualCount = InputView.readManualSelectionCount();
        PurchaseAmount purchaseAmount = new PurchaseAmount(price, manualCount);

        Lottos lottos = purchaseLotto(purchaseAmount);

        List<Integer> numbers= InputView.readWinnerNumber();
        int bonusNumber = InputView.readBonusNumber();
        WinningLotto winningLotto = new WinningLotto(numbers, bonusNumber);

        showResult(lottos, winningLotto, purchaseAmount);
    }

    private LottoGenerator lottoGenerator() {
        return new RandomLottoGenerator();
    }

    private Lottos purchaseLotto(PurchaseAmount purchaseAmount) {
        LottoMachine lottoMachine = new LottoMachine(lottoGenerator());

        for (int i = 0; i < purchaseAmount.getManualCount(); i++) {
            lottoMachine.manualSelection(InputView.readManualSelection());
        }
        List<Lotto> lottoList = lottoMachine.purchase(purchaseAmount.getRandomCount());
        ResultView.printLottoResult(purchaseAmount.getManualCount(),lottoList);

        return new Lottos(lottoList);
    }

    private void showResult(Lottos lottos, WinningLotto winningLotto, PurchaseAmount purchaseAmount) {
        Map<Rank, Integer> rankCount = lottos.getRankCount(winningLotto);

        LottoResult result = new LottoResult(rankCount);
        ResultView.printStats(result, purchaseAmount.getLottosCount());
    }
}
