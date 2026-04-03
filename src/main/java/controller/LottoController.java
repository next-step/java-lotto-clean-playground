package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoShop lottoShop;

    public LottoController(InputView inputView, OutputView outputView, LottoShop lottoShop) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoShop = lottoShop;
    }

    public void run() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(inputView.readAmount());
        Lottos lottos = lottoShop.purchase(purchaseAmount);

        outputView.printResultHeader(lottos.size());
        outputView.printLottos(lottos.toNumberLists());

        List<Integer> winningNumbers = inputView.readWinningNumbers();
        Lotto winningLotto = new Lotto(toLottoNumbers(winningNumbers));

        WinningStatistics winningStatistics = WinningStatistics.from(lottos, winningLotto);

        outputView.printWinningStatistics(winningStatistics.winningResults());
        outputView.printProfitRate(winningStatistics.calculateProfitRate(purchaseAmount));
    }

    private List<LottoNumber> toLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::new)
                .toList();
    }
}
