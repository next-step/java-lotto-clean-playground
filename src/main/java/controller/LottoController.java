package controller;

import model.dice.Dice;
import model.lotto.Lottos;
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
        Lottos lottos = createLottos();
        printLottosHistory(lottos);
    }

    private Lottos createLottos() {
        String moneyInput = receiveLottoMoneyInput();
        return Lottos.createWith(dice, moneyInput);
    }

    private String receiveLottoMoneyInput() {
        outputView.printAskInputLottoMoney();
        return inputView.inputLottoMoneyValue();
    }

    private void printLottosHistory(final Lottos lottos) {
        outputView.printLottoSize(lottos.getLottosSize());
        lottos.getTotalLottoNumbers()
                .forEach(outputView::printLottoNumbers);
    }
}
