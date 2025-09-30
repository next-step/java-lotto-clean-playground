package Controller;

import Model.Jackpot;
import Model.LottoMatcher;
import Model.Lottos;
import View.InputView;
import View.OutView;

public class LottoController {
    public void startLotto() {
        InputView inputView = new InputView();
        int money = inputView.getMoney();

        Lottos lottos = new Lottos(money);

        OutView outView = new OutView();

        outView.printLottos(lottos);

        Jackpot jackpot = new Jackpot(inputView.getJackpot());

        LottoMatcher lottoMatcher = new LottoMatcher(lottos, jackpot);

        outView.printLottoMatcher(lottoMatcher.getMatchCounts(), lottoMatcher.getRate(money));
    }
}
