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
        PurchaseAmount purchaseAmount = purchase();
        Lottos lottos = generateLotto(purchaseAmount);

        WinningLotto winningLotto = inputWinningNumber();
        BonusBall bonusBall = inputBonusBall(winningLotto);

        WinningStatistics statistics = new WinningStatistics(lottos, winningLotto, bonusBall);
        ResultView.printStatistics(statistics);

        double profitRate = statistics.calculateProfitRate(purchaseAmount.getAmount());
        ResultView.printProfitRate(profitRate);
    }

    private static PurchaseAmount purchase() {
        int amount = InputView.getLottoPurchaseAmount();
        return new PurchaseAmount(amount);
    }

    private static Lottos generateLotto(PurchaseAmount purchaseAmount) {
        int manualCount = InputView.getManualCount();
        int autoCount = purchaseAmount.calculateAutoCount(manualCount);
        List<List<Integer>> manualNumbers = InputView.getManualNumbers(manualCount);

        Lottos lottos = new Lottos();
        addManualLottos(lottos, manualNumbers);
        addAutoLottos(lottos, autoCount);

        ResultView.printPurchaseAmount(manualCount, autoCount);
        for (Lotto lotto : lottos.getLottos()) {
            ResultView.printLotto(lotto);
        }

        return lottos;
    }

    private static void addManualLottos(Lottos lottos, List<List<Integer>> manualNumbers) {
        for (List<Integer> manualNumber : manualNumbers) {
            List<LottoNumber> lottoNumbers = manualNumber.stream()
                    .map(LottoNumber::new)
                    .toList();
            lottos.add(new Lotto(lottoNumbers));
        }
    }

    private static void addAutoLottos(Lottos lottos, int autoCount) {
        for (int i = 0; i < autoCount; i++) {
            lottos.add(LottoGenerator.generateLotto());
        }
    }

    private static WinningLotto inputWinningNumber() {
        List<Integer> winningNumbersInput = InputView.getWinningNumbers();
        return new WinningLotto(winningNumbersInput);
    }

    private static BonusBall inputBonusBall(WinningLotto winningLotto) {
        int inputBonusNumber = InputView.getBonusBall();
        LottoNumber bonusNumber = new LottoNumber(inputBonusNumber);

        return new BonusBall(bonusNumber, winningLotto.getWinningNumbers());
    }
}
