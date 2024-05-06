package controller;

import model.BuyLotto;
import model.Lotto;
import model.ProfitCalculator;
import model.WinLotto;
import view.InputView;
import view.ResultView;

import java.util.List;

public class LottoController {

    public void lottoStart() {

        int price = InputView.getPrice();
        int manualCount = InputView.getManualCount();
        BuyLotto buyLotto = new BuyLotto(price, manualCount, InputView.getManualLottos(manualCount));
        List<Lotto> lottos = buyLotto
                .generateLotto();

        ResultView.printLottos(manualCount, lottos);

        WinLotto winLotto = new WinLotto(InputView.getWinLotto(), lottos, InputView.getBonusBall());
        winLotto.updateWinningStates(lottos);
        ResultView.printStatics(winLotto.getWinningStates());

        ProfitCalculator profitCalculator = new ProfitCalculator();
        ResultView.printProfit(profitCalculator.calculateProfit(winLotto.getWinPrize(), price));

    }


}
