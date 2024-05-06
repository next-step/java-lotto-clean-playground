package controller;

import domain.Lotto;
import domain.LottoNumber;
import domain.LottoStatistics;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoController {
    public static void main(String[] args) {
        LottoController controller = new LottoController();
        controller.run();
    }

    private void run() {
        int purchaseAmount = InputView.getPurchaseAmount();
        List<Lotto> lottos = Lotto.purchaseLottos(purchaseAmount);
        OutputView.printLottos(lottos);

        List<LottoNumber> winningNumbers = InputView.getWinningNumbers();
        LottoStatistics statistics = Lotto.getStatistics(lottos, winningNumbers);
        OutputView.printStatistics(statistics);
    }
}
