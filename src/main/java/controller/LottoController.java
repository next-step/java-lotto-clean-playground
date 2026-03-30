package controller;

import domain.*;
import dto.WinningResult;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final LottoShop lottoShop = new LottoShop(new RandomNumberGenerator());

    public void run() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(inputView.readAmount());
        Lottos lottos = lottoShop.purchase(purchaseAmount);
        outputView.printResultHeader(lottos.size());
        outputView.printLottos(lottos.toNumberLists());
        Lotto winningLotto = new Lotto(inputView.readWinningNumbers());
        WinningStatistics winningStatistics = lottos.createWinningStatistics(winningLotto);
        outputView.printWinningStatistics(createWinningResults(winningStatistics));
        outputView.printProfitRate(winningStatistics.calculateProfitRate(purchaseAmount));
    }

    private List<WinningResult> createWinningResults(WinningStatistics winningStatistics) {
        return List.of(
                createWinningResult(winningStatistics, Rank.THREE_MATCH),
                createWinningResult(winningStatistics, Rank.FOUR_MATCH),
                createWinningResult(winningStatistics, Rank.FIVE_MATCH),
                createWinningResult(winningStatistics, Rank.SIX_MATCH)
        );
    }

    private WinningResult createWinningResult(WinningStatistics winningStatistics, Rank rank) {
        return new WinningResult(rank.getMatchCount(), rank.getPrizeMoney(), winningStatistics.countOf(rank));
    }
}
