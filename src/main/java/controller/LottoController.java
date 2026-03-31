package controller;

import domain.*;
import dto.WinningResult;
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

        WinningStatistics winningStatistics = lottos.createWinningStatistics(winningLotto);

        outputView.printWinningStatistics(createWinningResults(winningStatistics));
        outputView.printProfitRate(winningStatistics.calculateProfitRate(purchaseAmount));
    }

    private List<LottoNumber> toLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::new)
                .toList();
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
