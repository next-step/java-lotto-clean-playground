package controller;

import domain.Lotto;
import domain.LottoResult;
import domain.PurchasePrice;
import domain.Lottos;
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
        printLottoResult(lottoResult, purchasePrice);
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

    private void printLottoResult(LottoResult lottoResult, PurchasePrice purchasePrice) {
        outputView.printLottoResultStart();
        printEachResult(lottoResult);
        outputView.printROI(lottoResult.getROI(purchasePrice));
    }

    private void printEachResult(LottoResult lottoResult) {
        final Map<Rank, Integer> rankCountMap = lottoResult.getRankCountMap();
        for (Rank rank : rankCountMap.keySet()) {
            if (rank == Rank.LAST_PLACE) {
                continue;
            }
            outputView.printLottoResult(rank.getScoreCutoff(), rank.getPrizeMoney(),
                                        rankCountMap.get(rank));
        }
    }

}
