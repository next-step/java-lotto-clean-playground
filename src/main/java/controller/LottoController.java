package controller;

import domain.BonusBall;
import domain.Lotto;
import domain.LottoResult;
import domain.Lottos;
import domain.PurchasePrice;
import domain.Rank;
import domain.WinningLotto;
import java.util.List;
import java.util.Map;
import service.LottoService;
import view.InputView;
import view.OutputView;

public class LottoController {

    private final static List<Rank> OUTPUT_ORDER_OF_RANK =
        List.of(Rank.FIFTH_PLACE, Rank.FOURTH_PLACE, Rank.THIRD_PLACE, Rank.SECOND_PLACE, Rank.FIRST_PLACE);

    private final OutputView outputView = new OutputView();
    private final InputView inputView = new InputView();
    private final LottoService lottoService = new LottoService();

    public void execute() {
        final PurchasePrice purchasePrice = getPurchasePrice();
        final Lottos lottos = lottoService.generateLottos(purchasePrice);
        printLottosStatus(lottos);

        final WinningLotto winningLotto = getWinningLotto();
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

    private WinningLotto getWinningLotto() {
        final Lotto winningNumbers = getWinningNumbers();
        final BonusBall bonusBall = getBonusBall();
        return new WinningLotto(winningNumbers, bonusBall);
    }

    private Lotto getWinningNumbers() {
        outputView.printInputWinningNumbers();
        final List<Integer> winningNumbers = inputView.getWinningNumbers();
        return new Lotto(winningNumbers);
    }

    private BonusBall getBonusBall() {
        outputView.printInputBonusNumber();
        final int bonusNumber = inputView.getUserIntegerInput();
        return new BonusBall(bonusNumber);
    }

    private void printLottoResultAndRoi(LottoResult lottoResult, PurchasePrice purchasePrice) {
        outputView.printLottoResultStart();
        printLottoResult(lottoResult);
        outputView.printROI(lottoResult.getROI(purchasePrice));
    }

    private void printLottoResult(LottoResult lottoResult) {
        final Map<Rank, Integer> rankCountMap = lottoResult.getRankCountMap();
        for (Rank rank : OUTPUT_ORDER_OF_RANK) {
            outputView.printLottoResult(rank.getScoreCutoff(), rank.getPrizeMoney(), rank.isBonusBallMatching(),
                                        rankCountMap.get(rank));
        }
    }

}
