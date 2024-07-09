package lotto.controller;

import lotto.model.Lotto;
import lotto.model.LottoMarket;
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
        final Lottos lottos = getLottos();

        outputView.printLottosHistory(lottos);

        final LottoResult result = getLottoResult(lottos);
        outputView.printLottoResult(result);
    }

    private Lottos getLottos() {
        final Money money = getMoney();
        final int customAmount = getCustomAmount();
        final Lottos lottosCustom = getCustomLottos(customAmount);

        return market.getLottos(money, lottosCustom);
    }

    private Money getMoney() {
        outputView.printAskInputMoney();
        final String money = inputView.getInput();

        return Money.from(money);
    }

    private int getCustomAmount() {
        outputView.printAskInputCustom();

        return inputView.getNumber();
    }

    private Lottos getCustomLottos(int size) {
        Lottos lottos = new Lottos();

        outputView.printAskInputLottoNumbers();

        for (int i = 0; i < size; i++) {
            String input = inputView.getInput();
            lottos.addCustom(Lotto.from(input));
        }

        return lottos;
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
