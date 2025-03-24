package controller;

import model.Lottos;
import model.NumbersGenerator;
import view.InputView;
import view.OutputView;

public class LottoController {

    private final int LOTTO_PRICE = 1000;
    private final NumbersGenerator numbersGenerator;

    public LottoController(NumbersGenerator numbersGenerator) {
        this.numbersGenerator = numbersGenerator;
    }

    public void run() {
        Lottos lottos = purchaseLottos();
        OutputView.printLottos(lottos);
    }

    private Lottos purchaseLottos() {
        OutputView.printPurchaseMessage();
        int amount = InputView.getInt() / LOTTO_PRICE;
        return Lottos.createLottos(amount, numbersGenerator);
    }

}
