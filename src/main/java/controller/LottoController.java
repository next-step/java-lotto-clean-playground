package controller;

import domain.Lotto;
import domain.LottoResult;
import domain.Lottos;
import domain.PurchasePrice;
import domain.Rank;
import java.util.List;
import java.util.Map;
import service.LottoService;
import view.InputView;
import view.OutputView;

public class LottoController {

    private final OutputView outputView = new OutputView();
    private final InputView inputView = new InputView();
    private final LottoService lottoService = new LottoService();

    public void execute() {
        final PurchasePrice purchasePrice = getPurchasePrice();
        final Lottos lottos = lottoService.generateLottos(purchasePrice);
        printLottosStatus(lottos);

        final Lotto winningLotto = getWinningLotto();
        final LottoResult lottoResult = lottoService.getLottoResult(winningLotto, lottos);
        printLottoResultAndRoi(lottoResult, purchasePrice);
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

    private void printLottoResultAndRoi(LottoResult lottoResult, PurchasePrice purchasePrice) {
        outputView.printLottoResultStart();
        printLottoResult(lottoResult);
        outputView.printROI(lottoResult.getROI(purchasePrice));
    }

    private void printLottoResult(LottoResult lottoResult) {
        final Map<Rank, Integer> rankCountMap = lottoResult.getRankCountMap();
        for (Rank rank : rankCountMap.keySet()) {
            printEachLottoResult(rank, rankCountMap);
        }
    }

    private void printEachLottoResult(Rank rank, Map<Rank, Integer> rankCountMap) {
        if (rank == Rank.LAST_PLACE) {
            return;
        }
        outputView.printLottoResult(rank.getScoreCutoff(), rank.getPrizeMoney(), rankCountMap.get(rank));
    }

}
