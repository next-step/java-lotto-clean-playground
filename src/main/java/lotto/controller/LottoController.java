package lotto.controller;

import lotto.model.LottoMarket;
import lotto.model.Lottos;
import lotto.utils.generator.NumberGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final NumberGenerator generator;

    public LottoController(InputView inputView, OutputView outputView, NumberGenerator generator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.generator = generator;
    }

    public void run() {
        final LottoMarket market = getMarketWithMoney();
        final Lottos lottos = createLottos(market);
        outputView.printLottosHistory(lottos);
    }

    private LottoMarket getMarketWithMoney() {
        final String money = getMoneyInput();
        return LottoMarket.from(money);
    }

    private String getMoneyInput() {
        outputView.printAskInputMoney();
        return inputView.getMoney();
    }

    private Lottos createLottos(final LottoMarket market) {
        final int lottoSize = market.getLottoSize();
        return Lottos.of(generator, lottoSize);
    }

}
