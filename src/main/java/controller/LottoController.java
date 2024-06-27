package controller;

import domain.Lotto;
import domain.Price;
import domain.Lottos;
import java.util.List;
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

        final Lotto winningLotto = getWinningLotto();
    }

    private Price getPrice() {
        outputView.printInputPriceGuide();
        final int userIntegerInput = inputView.getUserIntegerInput();
        return new Price(userIntegerInput);
    }

    private void printLottosStatus(Lottos lottos) {
        outputView.printNumberOfLotto(lottos.getSize());
        outputView.printStatusOfLottos(lottos.getStatus());
    }

    private Lotto getWinningLotto() {
        outputView.printInputWinningNumbers();
        final List<Integer> winningNumbers = inputView.getWinningNumbers();
        return new Lotto(winningNumbers);
    }

}
