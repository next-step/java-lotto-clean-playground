package controller;

import model.*;
import view.InputView;
import view.ResultView;

import java.util.List;

public class LottoController {

    public void lottoStart() {

        int price = InputView.getPrice();
        int manualCount = InputView.getManualCount();
        List<String> manualLottos = InputView.getManualLottos(manualCount);
        BuyLotto buyLotto = new BuyLotto(price, manualCount, manualLottos);
        LottoGenerator lottoGenerator = new LottoGenerator();
        List<Lotto> lottos = lottoGenerator.generateLottos(buyLotto.calculateAutoLottoCount(), manualLottos);

        ResultView.printLottos(manualCount, lottos);

        WinLotto winLotto = new WinLotto(InputView.getWinLotto(), lottos, InputView.getBonusBall());
        winLotto.updateWinningStates(lottos);
        ResultView.printStatics(winLotto.getWinningStates());

        ProfitCalculator profitCalculator = new ProfitCalculator();
        ResultView.printProfit(profitCalculator.calculateProfit(winLotto.getWinPrize(), price));

    }
}
