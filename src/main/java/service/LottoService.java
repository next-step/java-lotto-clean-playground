package service;

import domain.Lottos;
import domain.NumberGenerator;
import view.InputView;
import view.OutputView;

public class LottoService {

    private final OutputView outputView = new OutputView();
    private final InputView inputView = new InputView();

    private final static int LOTTO_PRICE = 1000;


    public Lottos getLottos(NumberGenerator numberGenerator) {
        final int numberOfLotto = getNumberOfLotto();
        return new Lottos(numberGenerator, numberOfLotto);
    }

    private int getNumberOfLotto() {
        int inputPrice = getInputPrice();
        if (inputPrice <= 0) {
            return 0;
        }
        return inputPrice / LOTTO_PRICE;
    }

    private int getInputPrice() {
        outputView.printInputPriceGuide();
        return inputView.getUserIntegerInput();
    }

    public void printStatusOfLottos(Lottos lottos) {
        outputView.printNumberOfLotto(lottos.getSize());
        outputView.printStatusOfLottos(lottos.getStatus());
    }

}
