package controller;

import domain.*;
import view.InputView;
import view.OutputView;

public class LottoController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final LottoShop lottoShop = new LottoShop(new RandomNumberGenerator());

    public void run() {
        int amount = inputView.readAmount();
        Lottos lottos = lottoShop.purchase(amount);
        outputView.printResultHeader(lottos.size());
        outputView.printLottos(lottos.toNumberLists());
        Lotto winningLotto = new Lotto(inputView.readWinningNumbers());
        WinningStatistics winningStatistics = lottos.createWinningStatistics(winningLotto);
    }
}
