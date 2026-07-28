package controller;

import domain.lotto.Lotto;
import domain.lotto.LottoGenerator;
import domain.lotto.LottoNumber;
import domain.lotto.Lottos;
import domain.purchase.PurchaseAmount;
import domain.winning.BonusBall;
import domain.winning.WinningLotto;
import domain.winning.WinningStatistics;
import java.util.List;
import view.InputView;
import view.ResultView;

public class LottoController {
    public static void run() {
        int amount = InputView.getLottoPurchaseAmount();
        PurchaseAmount purchaseAmount = new PurchaseAmount(amount);

        int manualCount = InputView.getManualCount();
        int autoCount = purchaseAmount.calculateAutoCount(manualCount);

        List<List<Integer>> manualNumbers = InputView.getManualNumbers(manualCount);

        Lottos lottos = new Lottos();

        for (List<Integer> manualNumber : manualNumbers) {
            List<LottoNumber> lottoNumbers = manualNumber.stream()
                    .map(LottoNumber::new)
                    .toList();
            Lotto lotto = new Lotto(lottoNumbers);
            lottos.add(lotto);
        }

        LottoGenerator lottoGenerator = new LottoGenerator();
        for (int i = 0; i < autoCount; i++) {
            Lotto lotto = lottoGenerator.generateLotto();
            lottos.add(lotto);
        }

        ResultView.printPurchaseAmount(manualCount, autoCount);
        for (Lotto lotto : lottos.getLottos()) {
            ResultView.printLotto(lotto);
        }

        List<Integer> winningNumbersInput = InputView.getWinningNumbers();
        WinningLotto winningLotto = new WinningLotto(winningNumbersInput);

        int inputBonusNumber = InputView.getBonusBall();
        LottoNumber bonusNumber = new LottoNumber(inputBonusNumber);

        BonusBall bonusBall = new BonusBall(bonusNumber, winningLotto.getWinningNumbers());

        WinningStatistics statistics = new WinningStatistics(lottos, winningLotto, bonusBall);
        ResultView.printStatistics(statistics);

        double profitRate = statistics.calculateProfitRate(amount);
        ResultView.printProfitRate(profitRate);
    }
}
