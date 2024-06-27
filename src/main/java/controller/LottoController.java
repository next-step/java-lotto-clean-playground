package controller;

import domain.Lotto;
import domain.PurchasePrice;
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
        final PurchasePrice purchasePrice = getPurchasePrice();
        final Lottos lottos = lottoService.getLottos(purchasePrice);
        printLottosStatus(lottos);

        final Lotto winningLotto = getWinningLotto();
    }

    private PurchasePrice getPurchasePrice() {
        outputView.printInputPurchasePriceGuide();
        final int userIntegerInput = inputView.getUserIntegerInput();
        return new PurchasePrice(userIntegerInput);
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
