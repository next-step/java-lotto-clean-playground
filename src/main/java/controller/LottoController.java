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
        String moneyInput = receiveLottoMoneyInput();

        Lottos lottos = Lottos.createWith(dice, moneyInput);
        outputView.printLottoSize(lottos.getLottosSize());

        printLottoHistory(lottos);
    }

    private String receiveLottoMoneyInput() {
        outputView.printAskInputLottoMoney();
        return inputView.inputLottoMoneyValue();
    }

    private void printLottoHistory(final Lottos lottos) {
        lottos.getTotalLottoNumbers()
                .forEach(outputView::printLottoNumbers);
    }
}
