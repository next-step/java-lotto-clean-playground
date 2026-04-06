package controller;

import domain.*;
import dto.WinningResult;
import view.InputView;
import view.OutputView;

import java.util.Arrays;
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
        int manualCount = inputView.readManualCount();
        Lottos manualLottos = toLottos(inputView.readManualNumbers(manualCount));
        Lottos lottos = lottoShop.purchase(purchaseAmount, manualLottos);

        outputView.printResultHeader(lottos.size());
        outputView.printLottos(lottos.toNumberLists());

        List<Integer> winningNumbers = inputView.readWinningNumbers();
        BonusBall bonusBall = new BonusBall(new LottoNumber(inputView.readBonusBall()));
        WinningLotto winningLotto = new WinningLotto(toLotto(winningNumbers), bonusBall);

        WinningStatistics winningStatistics = WinningStatistics.from(lottos, winningLotto);

        outputView.printWinningStatistics(createWinningResults(winningStatistics));
        outputView.printProfitRate(winningStatistics.calculateProfitRate(purchaseAmount));
    }

    private Lottos toLottos(List<List<Integer>> numbers) {
        return new Lottos(numbers.stream()
                .map(this::toLotto)
                .toList());
    }

    private Lotto toLotto(List<Integer> numbers) {
        return new Lotto(numbers.stream()
                .map(LottoNumber::new)
                .toList());
    }

    private List<WinningResult> createWinningResults(WinningStatistics winningStatistics) {
        return Arrays.stream(Rank.values())
                .filter(Rank::isWinning)
                .map(rank -> createWinningResult(winningStatistics, rank))
                .toList();
    }


    private WinningResult createWinningResult(WinningStatistics winningStatistics, Rank rank) {
        return WinningResult.from(rank, winningStatistics.countOf(rank));
    }
}
