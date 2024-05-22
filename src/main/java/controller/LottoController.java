package controller;

import model.dice.Dice;
import model.lotto.Lottos;
import model.lotto.vo.LottoMarket;
import view.input.InputView;
import view.output.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final Dice dice;

    public LottoController(final InputView inputView, final OutputView outputView, final Dice dice) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.dice = dice;
    }

    public void play() {
        LottoMarket market = prepareLottoMarketWithMoneyInput();
        Lottos lottos = createLottos(market);
        printLottosHistory(lottos);
    }

    private LottoMarket prepareLottoMarketWithMoneyInput() {
        String moneyInput = receiveLottoMoneyInput();
        return LottoMarket.from(moneyInput);
    }

    private String receiveLottoMoneyInput() {
        outputView.printAskInputLottoMoney();
        return inputView.inputLottoMoneyValue();
    }

    private Lottos createLottos(final LottoMarket market) {
        int makeLottoSize = market.calculateBoughtLottoSize();
        return Lottos.createWith(dice, makeLottoSize);
    }

    private void printLottosHistory(final Lottos lottos) {
        outputView.printLottoSize(lottos.getLottosSize());
        lottos.getTotalLottoNumbers()
                .forEach(outputView::printLottoNumbers);
    }
}
