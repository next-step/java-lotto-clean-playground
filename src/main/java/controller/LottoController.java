package controller;

import domain.Price;
import domain.Lottos;
import service.LottoService;
import view.InputView;
import view.OutputView;

public class LottoController {

    private final OutputView outputView = new OutputView();
    private final InputView inputView = new InputView();
    private final LottoService lottoService = new LottoService();

    public void execute() {
        final Price price = getPrice();
        final Lottos lottos = lottoService.getLottos(price);
        printLottosStatus(lottos);
    }

    private Price getPrice() {
        outputView.printInputPriceGuide();
        return inputView.getPrice();
    }

    private void printLottosStatus(Lottos lottos) {
        outputView.printNumberOfLotto(lottos.getSize());
        outputView.printStatusOfLottos(lottos.getStatus());
    }


}
