package lotto.controller;

import lotto.model.LottoMarket;
import lotto.model.LottoNumber;
import lotto.model.LottoResult;
import lotto.model.Lottos;
import lotto.model.Money;
import lotto.model.WinNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoMarket market;

    public LottoController(InputView inputView, OutputView outputView, LottoMarket market) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.market = market;
    }

    public void run() {
        final Lottos lottos = getLottosByMoney();
        outputView.printLottosHistory(lottos);

        final LottoResult result = getLottoResult(lottos);
        outputView.printLottoResult(result);
    }

    private Lottos getLottosByMoney() {
        final Money money = getMoney();
        return market.getLottosByMoney(money);
    }

    private Money getMoney() {
        outputView.printAskInputMoney();
        final String money = inputView.getInput();

        return Money.from(money);
    }

    private LottoResult getLottoResult(final Lottos lottos) {
        WinNumbers winNumbers = getWinNumbers();

        return LottoResult.of(lottos, winNumbers);
    }

    private WinNumbers getWinNumbers() {
        outputView.printAskInputWinNumbers();

        final String winNumbers = inputView.getInput();

        outputView.printAskInputBonusNumber();

        final String bonusNumber = inputView.getInput();

        return WinNumbers.of(winNumbers, bonusNumber);
    }



}
